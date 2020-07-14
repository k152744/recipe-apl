package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UpdateRecipe{

  private int id;
  private String name;
  private String contents;
  private String imageName;
  private RecipeCategory recipeCategory;

  private MultipartFile image;
  
}