package com.example.demo.app;

import com.example.demo.entity.Recipe;
import com.example.demo.service.RecipeService;
import com.example.demo.form.RecipeForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController{
  
  private final RecipeService recipeService;

  @Autowired
  public RecipeController(RecipeService recipeService){
    this.recipeService = recipeService;
  }

  @GetMapping("/new")
  public String newRecipe(Model model) {
    return "recipe/new";
  }

  @GetMapping("/index")
  public String index(Model model){
    List<RecipeForm> list = recipeService.getRecipeForms();
    model.addAttribute("recipeList", list);
    return "recipe/index";
  }

  @PostMapping("/index")
  public String create(@RequestParam("image") MultipartFile multipartFile,@RequestParam("name") String recipeName,@RequestParam("contents") String recipeContents){
    
    try{
      recipeService.postRecipe(recipeName,recipeContents,multipartFile.getOriginalFilename(),multipartFile.getBytes()); 
    } catch(Exception e){
      System.out.println(e);
    }

    return "redirect:index";
  }
}