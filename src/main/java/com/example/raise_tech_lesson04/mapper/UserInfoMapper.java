package com.example.raise_tech_lesson04.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.raise_tech_lesson04.entity.UserInfo;

//@Mapper
// アノテーションを付与したインターフェースに xml で定義した SQL が対応づけられる
// 該当のメソッドを実行した時の SQL は XML で定義する
@Mapper
public interface UserInfoMapper {
    List<UserInfo> selectAll();
}