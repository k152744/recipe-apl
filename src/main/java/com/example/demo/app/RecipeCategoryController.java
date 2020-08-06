package com.example.demo.app;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.entity.Recipe;
import com.example.demo.entity.RecipeCategory;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.form.RecipeForm;
import com.example.demo.mapper.RecipeMapper;
import com.example.demo.mapper.RegistrationUserMapper;
import com.example.demo.repository.RecipeCategoryRepository;
import com.example.demo.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class RecipeCategoryController {

  public final RecipeMapper recipeMapper;

  public final RecipeService recipeService;

  public final RecipeCategoryRepository recipeCategoryRepository;

  public final RegistrationUserMapper registrationUserMapper;

  public RecipeCategoryController(RecipeMapper recipeMapper, RecipeService recipeService,
      RecipeCategoryRepository recipeCategoryRepository, RegistrationUserMapper registrationUserMapper) {
    this.recipeMapper             = recipeMapper;
    this.recipeService            = recipeService;
    this.recipeCategoryRepository = recipeCategoryRepository;
    this.registrationUserMapper   = registrationUserMapper;
  }

  @GetMapping("category/{id}/index")
  public String index(@PathVariable("id") Integer id, Model model, HttpServletRequest httpServletRequest) {
    Optional<RecipeCategory> recipeCategory = recipeCategoryRepository.findById(id);
    List<RecipeForm> recipesOfCategoryIndex = recipeService.settingMyRecipes(recipeCategory.get().getRecipeCategoryList());

    List <RecipeCategory> recipeCategoryList = recipeCategoryRepository.findAll();

    // int userId = Integer.parseInt(httpServletRequest.getSession().getId());
    // RegistrationUser user = registrationUserMapper.findLoginName(username);

    model.addAttribute("recipeCategoryName", recipeCategory.get().getName());
    model.addAttribute("recipeList",recipesOfCategoryIndex);
    model.addAttribute("recipeCategoryList",recipeCategoryList);
    // model.addAttribute("loginUserId", userId);

    return "category/index";
  }
}