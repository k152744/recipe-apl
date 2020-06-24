package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.Account;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
  @Select("SELECT id, name, password FROM user WHERE name = #{name}")
	public Account findAccount(@Param("name") String name);
} 