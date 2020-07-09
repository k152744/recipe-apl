package com.example.demo.service;

import com.example.demo.entity.Recipe;
import com.example.demo.entity.RecipeCategory;
import com.example.demo.entity.RegistrationUser;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.form.RecipeForm;
import com.example.demo.mapper.RegistrationUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class RecipeService {

  private final RecipeRepository recipeRepository;

  @Autowired
  private RegistrationUserMapper registrationUserMapper;

  @Autowired
  public RecipeService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public Recipe postRecipe(String name, String contents, String imageName, byte[] image, RecipeCategory category ,HttpServletRequest httpServletRequest) {
    Recipe recipe = new Recipe();

    recipe.setName(name);
    recipe.setContents(contents);
    recipe.setImagename(imageName);
    recipe.setImagebinary(image);
    recipe.setRecipeCategory(category);
    recipe.setCreated(LocalDateTime.now());

    String username = httpServletRequest.getRemoteUser();
    RegistrationUser user = registrationUserMapper.findLoginName(username);
    recipe.setRegistrationUser(user);
    
    return recipeRepository.save(recipe);
  }

  public List<RecipeForm> settingRecipes(Page<Recipe> recipes) {
    var forms = new ArrayList<RecipeForm>();

    // RecipeをRecipeFormに
    recipes.forEach(recipe -> {
      RecipeForm form = new RecipeForm();

      form.setId(recipe.getId());
      form.setName(recipe.getName());
      form.setContents(recipe.getContents());
      form.setUserId(recipe.getRegistrationUser().getId());

      // 拡張子を取得
      form.setExtension(
          recipe.getImagename().substring(recipe.getImagename().length() - 4, recipe.getImagename().length()));

      if (form.getExtension().contains("png")) {
        // BASE64に変換
        form.setBase64string("data:image/png;base64," + Base64.getEncoder().encodeToString(recipe.getImagebinary()));
      } else {
        // BASE64に変換
        form.setBase64string("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(recipe.getImagebinary()));
      }

      forms.add(form);
    });

    return forms;
  }

  public List<RecipeForm> settingMyRecipes(List<Recipe> recipes) {
    var forms = new ArrayList<RecipeForm>();

    // RecipeをRecipeFormに
    recipes.forEach(recipe -> {
      RecipeForm form = new RecipeForm();

      form.setName(recipe.getName());
      form.setContents(recipe.getContents());

      // 拡張子を取得
      form.setExtension(
          recipe.getImagename().substring(recipe.getImagename().length() - 4, recipe.getImagename().length()));

      if (form.getExtension().contains("png")) {
        // BASE64に変換
        form.setBase64string("data:image/png;base64," + Base64.getEncoder().encodeToString(recipe.getImagebinary()));
      } else {
        // BASE64に変換
        form.setBase64string("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(recipe.getImagebinary()));
      }

      forms.add(form);
    });

    return forms;
  }

  /*public List<Recipe> search(String name){
    List<Recipe> result = recipeRepository.findAll();
    return result;
  }*/


  public Page<Recipe> getRecipes(Pageable pageable) {
    return recipeRepository.findAll(pageable);
  }

}