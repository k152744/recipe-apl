package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  @Column(name = "image_name")
  private String imageName;
  @Column(name = "image_binary")
  private byte[] image;
  @Column(name = "contents")
  private String contents;
  @CreatedDate
  private LocalDateTime created;
  
}