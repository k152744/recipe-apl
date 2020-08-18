package com.example.demo.form;

import javax.validation.constraints.Size;

import com.example.demo.entity.RecipeCategory;

import org.springframework.web.multipart.MultipartFile;

// import lombok.Data;
import lombok.NonNull;

// @Data
public class CreateRecipeForm {

  @NonNull
  @Size(min = 1, max = 255)
  private String name;

  @NonNull
  private String contents;
  private RecipeCategory category;
  private MultipartFile image;

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

  public RecipeCategory getCategory() {
    return this.category;
  }

  public void setCategory(RecipeCategory category) {
    this.category = category;
  }

  public MultipartFile getImage() {
    return this.image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

}