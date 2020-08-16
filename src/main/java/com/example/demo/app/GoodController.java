package com.example.demo.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Good;
import com.example.demo.entity.Recipe;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.form.RecipeForm;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GoodService;
import com.example.demo.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping()
public class GoodController {

  private final GoodService goodService;

  private final RecipeService recipeService;

  private final UserRepository userRepository;

  private final RecipeRepository recipeRepository;

  @Autowired
  public GoodController(GoodService goodService,RecipeService recipeService,
  UserRepository userRepository,RecipeRepository recipeRepository){
    this.goodService      = goodService;
    this.recipeService    = recipeService;
    this.userRepository   = userRepository;
    this.recipeRepository = recipeRepository;
  }

  @PostMapping("/like/create/{userid}/{recipeid}")
  public String create(@PathVariable("userid") Integer userId, @PathVariable("recipeid") Integer recipeId,Model model) {
    Good good = new Good();
    Optional<RegistrationUser> user = userRepository.findById(userId);
    Optional<Recipe> recipe = recipeRepository.findById(recipeId);

    good.setRegistrationUser(user.get());
    good.setRecipe(recipe.get());

    goodService.postLike(good);
    
    model.addAttribute("username", user.get().getName());
    model.addAttribute("userId", user.get().getId());

    return "good/show";
  }

  @GetMapping("/user/{id}/like/index")
  public String index(Model model, @PathVariable("id") Integer userId){
    Optional<RegistrationUser> loginUser = userRepository.findById(userId);
    
    List<Recipe> goodRecipe = new ArrayList<>();
    for(Good good : loginUser.get().getGoodList()){
      //エラー
      goodRecipe.add(good.getRecipe());
    }
    List<RecipeForm> myGoodRecipes = recipeService.settingMyRecipes(goodRecipe);
    model.addAttribute("myRecipes", myGoodRecipes);
    model.addAttribute("username", loginUser.get().getName());
    model.addAttribute("userId", loginUser.get().getId());

    return "user/good";
  }
}