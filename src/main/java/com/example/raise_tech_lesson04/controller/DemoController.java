package com.example.raise_tech_lesson04.controller;

import com.example.raise_tech_lesson04.entity.UserInfo;
import com.example.raise_tech_lesson04.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DemoController {
    //@Autowired合致するオブジェクトを探して自動生成してくれる
    private final UserInfoMapper userInfoMapper;

    @RequestMapping
    public  String index(Model iModel){
        //DBからデータ取得。mapper.xmlで関連付けられたSQLが呼ばれる
        List<UserInfo> list = userInfoMapper.selectAll();
        //対応するresourceファイルから参照するために、値を登録
        iModel.addAttribute("userInfo", list);
        return "index";
    }
}
