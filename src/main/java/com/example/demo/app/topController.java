package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping()
public class topController {
    
    @GetMapping
    public String top() {
      return "top";
    }
    
    @GetMapping("/login")
    public String login() {
      return "login";
    }

    @GetMapping("/signup")
    public String signUp() {
      return "sign-up";
    }

    @GetMapping("/new")
    public String newRecipe() {
      return "new";
    }

    @GetMapping("/index")
    public String index() {
      return "index";
    }
  }