package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.entity.DbUserDetails;

@Controller
@RequestMapping()
public class topController {
    
    @GetMapping
    public String top(Model model) {
      int loginUserId = 0;
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(authentication.getPrincipal() instanceof DbUserDetails){
        loginUserId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
        model.addAttribute("loginUser", loginUserId);
      }else{
        model.addAttribute("loginUser", "");
      }	
      return "top";
    }

  }