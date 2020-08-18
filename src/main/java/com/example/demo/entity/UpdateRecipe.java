package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

// import lombok.Data;

// @Data
public class UpdateRecipe{

  private int id;
  private String name;
  private String contents;
  private String imageName;
  private RecipeCategory recipeCategory;
  private MultipartFile image;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContents() {
    return this.contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public String getImageName() {
    return this.imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public RecipeCategory getRecipeCategory() {
    return this.recipeCategory;
  }

  public void setRecipeCategory(RecipeCategory recipeCategory) {
    this.recipeCategory = recipeCategory;
  }

  public MultipartFile getImage() {
    return this.image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }
  
}