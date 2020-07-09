package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.Recipe;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface GoodRecipeMapper{
  @Select("SELECT * FROM recipe INNER JOIN good ON good.user_id = #{user_id}")
  public List<Recipe> findGoodRecipes(@Param("user_id") Integer user_id);


}