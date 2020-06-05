package com.example.demo.service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//トランザクション処理をかける
@Transactional
public class RecipeService {
  
  @Autowired
  RecipeRepository recipeRepository;

  public Recipe postRecipe(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  public List<Recipe> getRecipe() {
    return recipeRepository.findAll();
  }

}