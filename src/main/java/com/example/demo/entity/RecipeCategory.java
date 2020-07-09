package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import org.springframework.data.annotation.CreatedDate;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.util.List;

@Entity
@Table(name="recipecategory")
public class RecipeCategory{

  @Id
  @Column(name="id")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  @Column(name="name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipeCategory",orphanRemoval = true)
  private List<Recipe> recipeCategoryList;

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

  public List<Recipe> getRecipeCategoryList() {
    return this.recipeCategoryList;
  }

  public void setRecipeCategoryList(List<Recipe> recipeCategoryList) {
    this.recipeCategoryList = recipeCategoryList;
  }
}