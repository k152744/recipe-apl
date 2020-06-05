package com.example.demo.service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.form.RecipeFrom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Base64;

@Service
//トランザクション処理をかける
@Transactional
public class RecipeService {
  
  @Autowired
  RecipeRepository recipeRepository;

  public Recipe postRecipe(String name, String contents, String imageName, byte[] image) {
    Recipe recipe = new Recipe();

    recipe.setName(name);
    recipe.setContents(contents);
    recipe.setImageName(imageName);
    recipe.setImage(image);
    
    return recipeRepository.save(recipe);
  }

  public List<Recipe> getRecipe() {
    return recipeRepository.findAll();
  }

  public List<RecipeFrom> getRecipeFroms(){
    List<RecipeFrom> forms = new ArrayList<RecipeFrom>();
    List<Recipe> recipes = getRecipe();

    for(Recipe recipe:recipes) {
      RecipeFrom form = new RecipeFrom();

      form.setName(recipe.getName());
      form.setContents(recipe.getContents());
      //拡張子を取得
      form.setExtension(recipe.getImageName().substring(recipe.getImageName().length() - 4, recipe.getImageName().length()));
      
      if(form.getExtension().contains("png")){
        //BASE64に変換
        form.setBase64string("data:image/png;base64," + Base64.getEncoder().encodeToString(recipe.getImage()));
      } else {
        //BASE64に変換
        form.setBase64string("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(recipe.getImage()));
      }
      
      forms.add(form);
  }

    return forms;
  }

}