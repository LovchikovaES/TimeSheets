package ru.catn.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.catn.core.model.TimeSheetsToApprove;
import ru.catn.core.repositories.TimeSheetRepository;

@Controller
public class TimeSheetsController {

    private TimeSheetRepository timeSheetRepository;

    public TimeSheetsController(TimeSheetRepository timeSheetRepository) {
        this.timeSheetRepository = timeSheetRepository;
    }

    @GetMapping({"/timesheets"})
    public String showTimeSheetsToApprove(Model model) {
        var timeSheets = timeSheetRepository.findByApprovedHours(null);
        model.addAttribute("timesheets", new TimeSheetsToApprove(timeSheets));
        return "timesheets";
    }

    @PostMapping(path = "/timesheets", params = "save")
    public RedirectView save(final TimeSheetsToApprove timeSheetsToApprove) {
        var timeSheetsToSave = timeSheetsToApprove.getTimeSheets();
        for (var timeSheet : timeSheetsToSave) {
            var approvedHours = timeSheet.getApprovedHours();
            if (approvedHours != null && approvedHours > 0) {
                timeSheetRepository.save(timeSheet);
            }
        }
        return new RedirectView("timesheets", true);
    }

    @PostMapping(path = "/timesheets", params = "approveAll")
    public RedirectView approveAll(final TimeSheetsToApprove timeSheetsToApprove) {
        var timeSheetsToSave = timeSheetsToApprove.getTimeSheets();
        for (var timeSheet : timeSheetsToSave) {
            timeSheet.setApprovedHours(timeSheet.getHours());
            timeSheetRepository.save(timeSheet);
        }
        return new RedirectView("timesheets", true);
    }
}
