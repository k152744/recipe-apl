package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "good")
@Data
public class Good{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private RegistrationUser registrationUser;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "recipe_id")
  private Recipe recipe;
  
}