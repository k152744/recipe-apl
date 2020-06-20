package com.example.demo.form;

import lombok.Data;

@Data
public class RecipeForm {
  
  private String name;
  private String extension;
  private String base64string;
  private String contents;

}