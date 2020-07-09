package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name= "recipe")
@Data
@NoArgsConstructor
public class Recipe {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name="imagename")
  private String imagename;
  @Column(name = "imagebinary")
  private byte[] imagebinary;
  @Column(name = "contents")
  private String contents;
 
  @ManyToOne
  @JoinColumn(name = "user_id")
  private RegistrationUser registrationUser;
  
  @ManyToOne
  @JoinColumn(name = "category_id")
  private RecipeCategory recipeCategory;
  
  @CreatedDate
  private LocalDateTime created;
  
}