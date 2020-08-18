package com.example.demo.form;

// import lombok.Data;

// @Data
public class RecipeForm {

  private int id;
  private String name;
  private String extension;
  private String base64string;
  private String contents;
  private int userId;

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

  public String getExtension() {
    return this.extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getBase64string() {
    return this.base64string;
  }

  public void setBase64string(String base64string) {
    this.base64string = base64string;
  }

  public String getContents() {
    return this.contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
  
}