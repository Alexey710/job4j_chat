package ru.job4j.chat.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.chat.model.User;
import ru.job4j.chat.repository.UserRepository;
import ru.job4j.chat.service.PostService;
import ru.job4j.chat.service.UserService;

@Controller
public class IndexControl {

    private final UserService userService;
    private final PostService postService;

    public IndexControl(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        User user = userService.getAuthenticatedUser();

        model.addAttribute("posts", postService.findAllPostByUserId(user.getId()));
        model.addAttribute("user", user.getUsername());
        return "index";
    }
}
