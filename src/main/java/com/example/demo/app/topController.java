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
  
  }