package ru.catn.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.catn.core.model.Role;
import ru.catn.core.model.User;
import ru.catn.core.repositories.RoleRepository;
import ru.catn.core.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    final private UserRepository userRepository;
    final private RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping({"/user/list"})
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        userRepository.save(user);
        return new RedirectView("/user/list", true);
    }

    @GetMapping("/user/edit/{id}")
    public String showUserById(Model model, @PathVariable(name = "id") Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user";
        } else {
            return "error";
        }
    }

    @GetMapping("/user/delete/{id}")
    public RedirectView userDelete(Model model, @PathVariable(name = "id") Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new RedirectView("/user/list", true);
        } else {
            return new RedirectView("/error", true);
        }
    }

    @ModelAttribute("allRoles")
    public List<Role> getAllRoles() {
        return (List<Role>) this.roleRepository.findAll();
    }

}
