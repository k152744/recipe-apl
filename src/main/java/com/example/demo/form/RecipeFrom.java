package com.example.demo.form;

/*
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;
*/

public class RecipeFrom {
  
  private String name;
  private String extension;
  private String base64string;
  private String contents;

  public RecipeFrom() {};

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return this.extension;
  }

  public void setBase64string(String base64string) {
    this.base64string = base64string;
  }

  public String getBase64string() {
    return this.base64string;
  }

  public String getContents() {
    return this.contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  /*
  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
  */
}