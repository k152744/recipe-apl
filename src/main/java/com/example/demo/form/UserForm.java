package com.example.demo.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

// import lombok.Data;

// @Data
public class UserForm{

  @NotEmpty
  private String name;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NotEmpty
  @Pattern(regexp = "^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$")
  private String email;

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @NotEmpty
  @Length(min = 4, max = 8, message = "パスワードは4～8文字で入力してください。")
  private String password;

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}