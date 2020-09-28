package ru.catn.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.catn.auth.CurrentUser;
import ru.catn.core.model.Task;
import ru.catn.core.model.TimeSheet;
import ru.catn.core.model.User;
import ru.catn.core.model.Workday;
import ru.catn.core.model.ids.TimeSheetId;
import ru.catn.core.repositories.TimeSheetRepository;
import ru.catn.core.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkdayController {

    private TimeSheetRepository timeSheetRepository;
    private UserRepository userRepository;

    private List<TimeSheet> removedTimeSheets = new ArrayList<>();

    public WorkdayController(TimeSheetRepository timeSheetRepository, UserRepository userRepository) {
        this.timeSheetRepository = timeSheetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping({"/workday"})
    public String showWorkday(Model model, @RequestParam(name = "date") String date) {
        LocalDate workDate;
        removedTimeSheets.clear();
        if (date.isEmpty()) {
            workDate = LocalDate.now();
        } else {
            workDate = LocalDate.parse(date);
        }

        User user = userRepository.findById(CurrentUser.get().getId()).get();
        var timeSheets = timeSheetRepository.findByIdUserIdAndIdDate(user.getId(), workDate);

        if (timeSheets.size() == 0) {
            var newTimeSheet = new TimeSheet();
            var newTimeSheetId = new TimeSheetId();
            newTimeSheetId.setDate(workDate);
            newTimeSheetId.setUser(user);
            newTimeSheet.setId(newTimeSheetId);
            timeSheets.add(newTimeSheet);
        }
        model.addAttribute("workday", new Workday(timeSheets, workDate, user));
        return "workday";
    }

    @PostMapping(path = "/workday", params = "addTask")
    public String addTask(final Workday workday) {
        workday.addTimeSheet(new TimeSheet());
        return "workday";
    }

    @PostMapping(path = "/workday", params = "removeTask")
    public String removeTask(final Workday workday, final HttpServletRequest req) {
        final int rowId = Integer.valueOf(req.getParameter("removeTask"));
        this.removedTimeSheets.add(workday.getTimeSheets().get(rowId));
        workday.removeTimeSheet(rowId);
        return "workday";
    }

    @PostMapping(path = "/workday", params = "save")
    public String saveTask(final Workday workday) {
        var timeSheetsToSave = workday.getTimeSheets();
        for (var timeSheet : timeSheetsToSave) {
            timeSheetRepository.save(timeSheet);
        }

        for (var timeSheet : this.removedTimeSheets) {
            timeSheetRepository.delete(timeSheet);
        }
        removedTimeSheets.clear();
        return "workday";
    }

    @ModelAttribute("allUserTasks")
    public List<Task> getAllUserTasks() {
        var user = userRepository.findById(CurrentUser.get().getId());
        if (user.isPresent()) {
            return user.get().getTasks();
        } else {
            return new ArrayList<>();
        }
    }
}
