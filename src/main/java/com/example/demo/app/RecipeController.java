package com.example.demo.app;

import com.example.demo.entity.Recipe;
import com.example.demo.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController{
  @Autowired
  RecipeService recipeService;

  @GetMapping("/new")
  public String newRecipe(Model model) {
    return "recipe/new";
  }

  @GetMapping("/index")
  public String index(Model model){
    List<Recipe> list = recipeService.getRecipe();
    model.addAttribute("recipeList", list);
    return "recipe/index";
  }

  @PostMapping("/index")
  public String create(@ModelAttribute Recipe recipe){
    recipeService.postRecipe(recipe);
    return "redirect:index";
  }
}