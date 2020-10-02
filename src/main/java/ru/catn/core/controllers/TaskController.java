package ru.catn.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.catn.core.model.Task;
import ru.catn.core.model.User;
import ru.catn.core.repositories.TaskRepository;
import ru.catn.core.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
public class TaskController {
    final private TaskRepository taskRepository;
    final private UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping({"/task/list"})
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }

    @GetMapping("/task/create")
    public String taskCreateView(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @GetMapping("/task/{taskId}")
    public String showTaskById(Model model, @PathVariable(name = "taskId") Integer taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "task";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/task/*", params = "addUser")
    public String addUser(final Task task) {
        task.addUser(new User());
        return "task";
    }

    @PostMapping(path = "/task/*", params = "removeUser")
    public String removeUser(final Task task, final HttpServletRequest req) {
        final int rowId = Integer.parseInt(req.getParameter("removeUser"));
        task.deleteUser(rowId);
        return "task";
    }

    @PostMapping(path = "/task/*", params = "saveTask")
    public RedirectView saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return new RedirectView("/task/list", true);
    }

    @PostMapping(path = "/task/*", params = "deleteTask")
    public RedirectView taskDelete(@ModelAttribute Task task) {
        taskRepository.delete(task);
        return new RedirectView("/task/list", true);
    }

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

}
