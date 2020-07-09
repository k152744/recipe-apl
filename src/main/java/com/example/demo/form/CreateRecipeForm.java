package com.example.demo.form;

import javax.validation.constraints.Size;

import com.example.demo.entity.RecipeCategory;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateRecipeForm {

  
  private MultipartFile image;

  @NonNull
  @Size(min = 1, max = 255)
  private String name;

  @NonNull
  private String contents;

  private RecipeCategory category;

}