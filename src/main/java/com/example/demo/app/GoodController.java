package com.example.demo.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.entity.Good;
import com.example.demo.entity.Recipe;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.form.RecipeForm;
import com.example.demo.mapper.GoodRecipeMapper;
import com.example.demo.mapper.RegistrationUserMapper;
import com.example.demo.service.GoodService;
import com.example.demo.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping()
public class GoodController{

  @Autowired
  GoodService goodService;

  @Autowired
  RegistrationUserMapper registrationUserMapper;

  @Autowired
  GoodRecipeMapper goodRecipeMapper;

  @Autowired
  private RecipeService recipeService;

  @PostMapping("/like/create/{userid}/{recipeid}")
  public String likeCreate(@PathVariable("userid") Integer userId,@PathVariable("recipeid") Integer recipeId){
    Good good = new Good();

    good.setUserId(userId);
    good.setRecipeId(recipeId);

    goodService.postLike(good);
    return "recipe/new";
  }

  @GetMapping("/like/index")
  public String likeIndex(Model model, HttpServletRequest httpServletRequest){
    String username = httpServletRequest.getRemoteUser();
    model.addAttribute("username", username);

    RegistrationUser user = registrationUserMapper.findLoginName(username);
    int userId = user.getId();
    model.addAttribute("userId", userId);

    var goodRecipes = goodRecipeMapper.findGoodRecipes(userId);
    List<RecipeForm> myGoodRecipes = recipeService.settingMyRecipes(goodRecipes);
    model.addAttribute("myRecipes", myGoodRecipes);

    return "user/good";
  }

}