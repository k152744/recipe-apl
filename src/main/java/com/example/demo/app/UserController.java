package com.example.demo.app;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController{
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
      this.userService = userService;
    }

    @GetMapping("/signup")
    public String newUser(Model model) {
      return "user/sign-up";
    }

    @PostMapping("/signup")
    public String create(@ModelAttribute User user){
      userService.postUser(user);
      return "redirect:recipe/index";
    }
}
