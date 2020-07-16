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
import com.example.demo.entity.Recipe;
import com.example.demo.entity.RecipeCategory;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.entity.UpdateRecipe;
import com.example.demo.repository.RecipeCategoryRepository;
import com.example.demo.repository.RecipeRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

  private final RecipeService recipeService;

  private final RecipeMapper recipeMapper;

  private final RegistrationUserMapper registrationUserMapper;

  private final RecipeCategoryRepository recipeCategoryRepository;

  private final RecipeRepository recipeRepository;

  @Autowired
  public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper,
    RegistrationUserMapper registrationUserMapper, RecipeCategoryRepository recipeCategoryRepository, RecipeRepository recipeRepository) {
      this.recipeService            = recipeService;
      this.recipeMapper             = recipeMapper;
      this.registrationUserMapper   = registrationUserMapper;
      this.recipeCategoryRepository = recipeCategoryRepository;
      this.recipeRepository         = recipeRepository;
  }

  @GetMapping("/new")
  public String newRecipe(Model model) {
    List<RecipeCategory> categoryAll = recipeCategoryRepository.findAll();
    model.addAttribute("categoryAll", categoryAll);
    return "recipe/new";
  }

  @GetMapping("/index")
  public String index(Model model,
      @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
      HttpServletRequest httpServletRequest) {
        var pageRecipe = recipeService.getRecipes(pageable);
        var recipes = recipeService.settingRecipes(pageRecipe);

        String username = httpServletRequest.getRemoteUser();
        RegistrationUser user = registrationUserMapper.findLoginName(username);
        List <RecipeCategory> recipeCategoryList = recipeCategoryRepository.findAll();

        model.addAttribute("recipeList", recipes);
        model.addAttribute("recipeCategoryList",recipeCategoryList);
        model.addAttribute("page", pageRecipe);
        model.addAttribute("url", "/recipe/index");
        model.addAttribute("loginUserId", user.getId());
        return "recipe/index";
  }

  @PostMapping("/index")
  public String create(@Validated CreateRecipeForm form, BindingResult result, HttpServletRequest httpServletRequest) {
    try {
      recipeService.postRecipe(form.getName(), form.getContents(), form.getImage().getOriginalFilename(),
          form.getImage().getBytes(), form.getCategory(), httpServletRequest);
    } catch (Exception e) {
      System.out.println(e);
    }
    return "redirect:index";
  }

  @PostMapping("/index/research")
  public String search(Model model, @RequestParam("recipeName") String recipeName) {
    var recipes = recipeMapper.searchRecipes(recipeName);
    var searchRecipes = recipeService.settingMyRecipes(recipes);
    model.addAttribute("recipeList", searchRecipes);
    return "recipe/index";
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable Integer id, Model model) {
    Optional<Recipe> recipe = recipeRepository.findById(id);
    UpdateRecipe updateRecipe = new UpdateRecipe();

    updateRecipe.setId(recipe.get().getId());
    updateRecipe.setName(recipe.get().getName());
    updateRecipe.setImageName(recipe.get().getImagename());
    updateRecipe.setContents(recipe.get().getContents());
    updateRecipe.setRecipeCategory(recipe.get().getRecipeCategory());

    model.addAttribute("updateRecipe", updateRecipe);
    List<RecipeCategory> categoryAll = recipeCategoryRepository.findAll();
    model.addAttribute("categoryAll", categoryAll);

    return "recipe/edit";
  }

  @PostMapping("/update")
  public String update(@Validated UpdateRecipe updateRecipe) throws IOException {
    Optional<Recipe> recipe = recipeRepository.findById(updateRecipe.getId());

    recipe.get().setName(updateRecipe.getName());
    recipe.get().setContents(updateRecipe.getContents());
    recipe.get().setRecipeCategory(updateRecipe.getRecipeCategory());

    if(!updateRecipe.getImage().getOriginalFilename().isEmpty()){
      recipe.get().setImagename(updateRecipe.getImage().getOriginalFilename());
      recipe.get().setImagebinary(updateRecipe.getImage().getBytes());
    }
   
    recipeService.updateRecipe(recipe.get()); 

    return "recipe/update";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Integer id){
    Optional<Recipe> recipe = recipeRepository.findById(id);
    recipeService.deleteRecipe(recipe.get());
    return "recipe/delete";
  }

}