package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import lombok.Data;

import javax.persistence.OneToMany;

import java.util.List;

@Entity
@Table(name="recipecategory")
// @Data
public class RecipeCategory{

  @Id
  @Column(name="id")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name="name")
  private String name;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipeCategory",orphanRemoval = true)
  private List<Recipe> recipeCategoryList;

  public List<Recipe> getRecipeCategoryList() {
    return this.recipeCategoryList;
  }

  public void setRecipeCategoryList(List<Recipe> recipeCategoryList) {
    this.recipeCategoryList = recipeCategoryList;
  }

}