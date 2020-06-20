package com.example.demo.service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.form.RecipeForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;
import java.util.Base64;

@Service
@Transactional
public class RecipeService {

  private final RecipeRepository recipeRepository;

  @Autowired
  public RecipeService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public Recipe postRecipe(String name, String contents, String imageName, byte[] image) {
    Recipe recipe = new Recipe();

    recipe.setName(name);
    recipe.setContents(contents);
    recipe.setImageName(imageName);
    recipe.setImage(image);
    recipe.setCreated(LocalDateTime.now());

    return recipeRepository.save(recipe);
  }

  public List<RecipeForm> settingRecipes(Page<Recipe> recipes) {
    var forms = new ArrayList<RecipeForm>();

    // RecipeをRecipeFormに
    recipes.forEach(recipe -> {
      RecipeForm form = new RecipeForm();

      form.setName(recipe.getName());
      form.setContents(recipe.getContents());

      // 拡張子を取得
      form.setExtension(
          recipe.getImageName().substring(recipe.getImageName().length() - 4, recipe.getImageName().length()));

      if (form.getExtension().contains("png")) {
        // BASE64に変換
        form.setBase64string("data:image/png;base64," + Base64.getEncoder().encodeToString(recipe.getImage()));
      } else {
        // BASE64に変換
        form.setBase64string("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(recipe.getImage()));
      }

      forms.add(form);
    });

    return forms;
  }

  public Page<Recipe> getRecipes(Pageable pageable) {
    return recipeRepository.findAll(pageable);
  }

}