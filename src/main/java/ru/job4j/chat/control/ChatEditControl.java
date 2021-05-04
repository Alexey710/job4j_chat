package ru.job4j.chat.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Post;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.PostService;
import ru.job4j.chat.service.UserService;

@Controller
public class ChatEditControl {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/chat")
    public String getChat(@RequestParam(value = "id", required = false) String id,
                              @RequestParam(value = "name", required = false) String name,
                              Model model) {
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        Post post = postService.findById(Long.parseLong(id));
        model.addAttribute("chatName", post.getName());
        model.addAttribute("chat", post.getMessages());
        return "post/chat";
    }

    @PostMapping("/chat")
    public String addMessage(
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "id", required = false) String id,
            Model model) {

        Post post = postService.findById(Long.parseLong(id));
        post.getMessages().add(Message.of(message, userService.getAuthenticatedUser()));

        postService.create(post);
        model.addAttribute("chatName", post.getName());
        model.addAttribute("id", id);
        model.addAttribute("chat", post.getMessages());

        return "post/chat";
    }
}
