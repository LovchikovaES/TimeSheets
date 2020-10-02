package ru.catn.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.catn.core.ReportTask;
import ru.catn.core.model.Task;
import ru.catn.core.model.User;
import ru.catn.core.repositories.TaskRepository;
import ru.catn.core.repositories.TimeSheetRepository;
import ru.catn.core.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ReportTaskController {
    final private TaskRepository taskRepository;
    final private UserRepository userRepository;
    final private TimeSheetRepository timeSheetRepository;

    public ReportTaskController(TaskRepository taskRepository, UserRepository userRepository, TimeSheetRepository timeSheetRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.timeSheetRepository = timeSheetRepository;
    }

    @GetMapping({"/report_task"})
    public String showReportTask(Model model, @RequestParam(name = "task") String taskId) {
        ReportTask reportTask = new ReportTask();

        if (!taskId.isEmpty()) {
            Optional<Task> task = taskRepository.findById(Integer.parseInt(taskId));
            if (task.isPresent()) {
                model.addAttribute("selectedTask", task.get());

                reportTask.setTaskId(Integer.parseInt(taskId));
                var userApprovedHours = timeSheetRepository.findUserApprovedHoursByTaskAsArray(reportTask.getTaskId());
                reportTask.setUserApprovedHours(userApprovedHours);
            }
        }
        model.addAttribute("reportTask", reportTask);
        return "reportTask";
    }

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    @ModelAttribute("allTasks")
    public List<Task> getAllTasks() {
        return (List<Task>) this.taskRepository.findAll();
    }
}