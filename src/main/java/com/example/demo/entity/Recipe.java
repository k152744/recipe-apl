package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

// import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name= "recipe")
// @Data
@NoArgsConstructor
public class Recipe {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "name")
  private String name;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name="imagename")
  private String imagename;

  public String getImagename() {
    return this.imagename;
  }

  public void setImagename(String imagename) {
    this.imagename = imagename;
  }

  @Column(name = "imagebinary")
  private byte[] imagebinary;

  public byte[] getImagebinary() {
    return this.imagebinary;
  }

  public void setImagebinary(byte[] imagebinary) {
    this.imagebinary = imagebinary;
  }

  @Column(name = "contents")
  private String contents;

  public String getContents() {
    return this.contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }
 
  @ManyToOne
  @JoinColumn(name = "user_id")
  private RegistrationUser registrationUser;

  public RegistrationUser getRegistrationUser() {
    return this.registrationUser;
  }

  public void setRegistrationUser(RegistrationUser registrationUser) {
    this.registrationUser = registrationUser;
  }
  
  @ManyToOne
  @JoinColumn(name = "category_id")
  private RecipeCategory recipeCategory;

  public RecipeCategory getRecipeCategory() {
    return this.recipeCategory;
  }

  public void setRecipeCategory(RecipeCategory recipeCategory) {
    this.recipeCategory = recipeCategory;
  }
  
  @CreatedDate
  private LocalDateTime created;

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
  
}