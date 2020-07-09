package com.example.demo.app;

import com.example.demo.service.RecipeService;
import com.example.demo.form.CreateRecipeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import com.example.demo.mapper.RecipeMapper;
import com.example.demo.mapper.RegistrationUserMapper;
import com.example.demo.entity.RecipeCategory;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.repository.RecipeCategoryRepository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

  private final RecipeService recipeService;

  @Autowired
  private RecipeMapper recipeMapper;

  @Autowired
  private RegistrationUserMapper registrationUserMapper;

  @Autowired
  private RecipeCategoryRepository recipeCategoryRepository;

  @Autowired
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/new")
  public String newRecipe(Model model) {
    List<RecipeCategory> category = recipeCategoryRepository.findAll();
    model.addAttribute("category", category);
    return "recipe/new";
  }

  @GetMapping("/index")
  public String index(Model model,
      @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,HttpServletRequest httpServletRequest) {
    var pageRecipe = recipeService.getRecipes(pageable);
    var recipes = recipeService.settingRecipes(pageRecipe);
    
    String username = httpServletRequest.getRemoteUser();
    RegistrationUser user = registrationUserMapper.findLoginName(username);

    model.addAttribute("recipeList", recipes);
    model.addAttribute("page", pageRecipe);
    model.addAttribute("url", "/recipe/index");
    model.addAttribute("loginUserId", user.getId());
    return "recipe/index";
  }

  @PostMapping("/index")
  public String create(@Validated CreateRecipeForm form, BindingResult result,HttpServletRequest httpServletRequest) {

    try {
      recipeService.postRecipe(form.getName(), form.getContents() 
           , form.getImage().getOriginalFilename(), form.getImage().getBytes(),form.getCategory(),httpServletRequest);
    } catch (Exception e) {
      System.out.println(e);
    }
    return "redirect:index";
  }

  @PostMapping("/index/research")
  public String search(Model model, @RequestParam("recipeName") String recipeName){
    var recipes = recipeMapper.searchRecipes(recipeName);
    var searchRecipes = recipeService.settingMyRecipes(recipes);
    model.addAttribute("recipeList",searchRecipes);
    return "recipe/index";
  }
}