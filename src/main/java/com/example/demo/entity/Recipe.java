package com.example.demo.entity;

import javax.persistence.*;
/*
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;
*/

@Entity
@Table(name= "recipe")

public class Recipe {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  
  private String name;
  private String image;
  private String contents;

  /*
  @CreatedDate
  private LocalDateTime created;
  */
  public Recipe() {};

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

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
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