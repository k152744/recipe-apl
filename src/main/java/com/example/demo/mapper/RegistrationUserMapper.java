package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.RegistrationUser;

@Mapper
public interface RegistrationUserMapper {
  @Select("SELECT * FROM user WHERE name = #{name}")
  public RegistrationUser findLoginName(@Param("name") String name);

  @Select("SELECT * FROM user WHERE id = #{id}")
  public RegistrationUser findLoginId(@Param("id") Integer id);
}