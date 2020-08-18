package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

// import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "user")
// @Data
public class RegistrationUser{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String name;
  private String email;
  private String password;

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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @CreatedDate
  private LocalDateTime created;

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
  
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "registrationUser",orphanRemoval = true)
  private List<Recipe> recipeList;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "registrationUser", orphanRemoval = true)
  private List<Good> goodList;

  public List<Recipe> getRecipeList() {
    return this.recipeList;
  }

  public void setRecipeList(List<Recipe> recipeList) {
    this.recipeList = recipeList;
  }

  public List<Good> getGoodList() {
    return this.goodList;
  }

  public void setGoodList(List<Good> goodList) {
    this.goodList = goodList;
  }

}