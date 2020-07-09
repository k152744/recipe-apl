package com.example.demo.form;

import lombok.Data;

@Data
public class RecipeForm {
  
  private int id;
  private String name;
  private String extension;
  private String base64string;
  private String contents;
  private int userId;
}