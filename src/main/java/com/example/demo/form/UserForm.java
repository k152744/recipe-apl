package com.example.demo.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserForm{

  @NotEmpty
  private String name;
  @NotEmpty
  @Pattern(regexp = "^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$")
  private String email;
  @NotEmpty
  @Length(min = 4, max = 8, message = "パスワードは4～8文字で入力してください。")
  private String password;

}