package com.example.demo.app;

import com.example.demo.service.RegisterUserService;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.entity.UpdateUser;
import com.example.demo.form.RecipeForm;
import com.example.demo.form.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.mapper.RegistrationUserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.mapper.RecipeMapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
  private UserRepository userRepository;

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
    return "redirect:user/show";
  }

  @GetMapping("/user/show")
  public String userProfile(Model model, HttpServletRequest httpServletRequest) {
    String username = httpServletRequest.getRemoteUser();
    model.addAttribute("username", username);

    RegistrationUser user = registrationUserMapper.findLoginName(username);
    int userId = user.getId();
    model.addAttribute("userId", userId);

    var recipes = recipeMapper.findRecipes(userId);
    List<RecipeForm> myRecipes = recipeService.settingMyRecipes(recipes);
    model.addAttribute("myRecipes", myRecipes);

    return "user/show";
  }

  @GetMapping("/user/{id}/edit")
  public String userEdit(@PathVariable Integer id, Model model) {
    RegistrationUser user = registrationUserMapper.findLoginId(id);
    UpdateUser updateUser = new UpdateUser();

    updateUser.setId(user.getId());
    updateUser.setName(user.getName());
    updateUser.setEmail(user.getEmail());

    model.addAttribute("updateUser", updateUser);
    return "user/edit";
  }

  @PostMapping("/user/update")
  public String userUpdate(@Validated UpdateUser updateUser,Model model) {

    RegistrationUser user = registrationUserMapper.findLoginId(updateUser.getId());

    user.setName(updateUser.getName());
    user.setEmail(updateUser.getEmail());

    registerUserService.updateUser(user);

    model.addAttribute("username", user.getName());

    return "user/show";
  }

  @PostMapping("/user/{id}/delete")
  public String userDelete(@PathVariable Integer id){
    RegistrationUser user = registrationUserMapper.findLoginId(id);
    registerUserService.deleteUser(user);
    return "top";
  }
}
