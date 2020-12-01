package com.example.raise_tech_lesson04.mapper;

import java.util.List;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import com.example.raise_tech_lesson04.entity.UserInfo;

@Mapper
public class UserInfoMapper {
    List<UserInfo> selectAll();
}