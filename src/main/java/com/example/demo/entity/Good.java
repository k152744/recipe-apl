package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name= "good")
@Data
//@NoArgsConstructor
public class Good{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  @Column(name = "user_id")
  private int userId;
  @Column(name = "recipe_id")
  private int recipeId;

  
  /*@ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private RegistrationUser registrationUser;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
  private Recipe recipe;
  */
}
