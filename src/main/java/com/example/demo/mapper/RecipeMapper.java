package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.Recipe;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RecipeMapper{
  @Select("SELECT * FROM recipe WHERE user_id = #{user_id}")
  public List<Recipe> findRecipes(@Param("user_id") Integer user_id);

  @Select("SELECT * FROM recipe WHERE name LIKE CONCAT('%', #{name},'%') ")
  public List<Recipe> searchRecipes(@Param("name") String name);

}