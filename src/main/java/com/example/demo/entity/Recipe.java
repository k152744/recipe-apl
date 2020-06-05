package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
  @Column(name = "image_name")
  private String imageName;
  @Column(name = "image_binary")
  private byte[] image;
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

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImageName() {
    return this.imageName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getImage() {
    return this.image;
  }

  public void setImage(byte[] image) {
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