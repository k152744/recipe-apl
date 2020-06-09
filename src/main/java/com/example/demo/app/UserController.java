package com.example.demo.app;

import com.example.demo.service.RegisterUserService;
import com.example.demo.entity.User;
import com.example.demo.form.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController {
    
    private final RegisterUserService registerUserService;

    @Autowired
    public UserController(RegisterUserService registerUserService){
      this.registerUserService = registerUserService;
    }
    
    @GetMapping("/signup")
    public String newUser(Model model) {
      model.addAttribute(new UserForm());
      return "user/sign-up";
    }

    @PostMapping("/signup")
    public String create(@Validated UserForm userform){

      User user = new User();

      user.setName(userform.getName());
      user.setEmail(userform.getEmail());
      user.setPassword(userform.getPassword());

      registerUserService.postUser(user);
      return "redirect:recipe/new";
    }
}
