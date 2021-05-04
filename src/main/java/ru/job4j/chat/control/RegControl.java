package ru.job4j.chat.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.chat.model.Authority;
import ru.job4j.chat.model.User;
import ru.job4j.chat.repository.UserRepository;
import ru.job4j.chat.service.PostService;
import ru.job4j.chat.service.UserService;

@Controller
public class RegControl {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegControl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority authority = new Authority();
        authority.setId(1);
        user.setAuthority(authority);
        System.out.println(user);
        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }
}
