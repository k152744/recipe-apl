package com.example.demo.app;

import com.example.demo.service.RegisterUserService;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.form.RecipeForm;
import com.example.demo.form.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.mapper.RegistrationUserMapper;
import com.example.demo.mapper.RecipeMapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.example.demo.service.RecipeService;

@Controller
@RequestMapping()
public class UserController {

  @Autowired
  private RegisterUserService registerUserService;

  @Autowired
  private RegistrationUserMapper registrationUserMapper;

  @Autowired
  private RecipeMapper recipeMapper;

  @Autowired
  private RecipeService recipeService;

  public UserController(RegisterUserService registerUserService) {
    this.registerUserService = registerUserService;
  }

  @GetMapping("/signup")
  public String newUser(Model model) {
    model.addAttribute(new UserForm());
    return "user/sign-up";
  }

  @PostMapping("/signup")
  public String create(@Validated UserForm userform) {

    RegistrationUser user = new RegistrationUser();

    user.setName(userform.getName());
    user.setEmail(userform.getEmail());
    user.setPassword(userform.getPassword());

    registerUserService.postUser(user);
    return "redirect:recipe/new";
  }

  @GetMapping("user/show")
  public String userProfile(Model model, HttpServletRequest httpServletRequest) {
    String username = httpServletRequest.getRemoteUser();
    model.addAttribute("username", username);

    RegistrationUser user = registrationUserMapper.findLoginName(username);
    int userId = user.getId();
    var recipes = recipeMapper.findRecipes(userId);

    List<RecipeForm> myRecipes = recipeService.settingMyRecipes(recipes);
      model.addAttribute("myRecipes", myRecipes);

      return "user/show";
    }
}
