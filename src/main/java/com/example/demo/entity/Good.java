package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// import lombok.Data;

@Entity
@Table(name= "good")
// @Data
public class Good{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "recipe_id")
  private Recipe recipe;

  public Recipe getRecipe() {
    return this.recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }
  
}