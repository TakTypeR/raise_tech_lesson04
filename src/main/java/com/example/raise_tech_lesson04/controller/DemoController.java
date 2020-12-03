package com.example.raise_tech_lesson04.controller;

import com.example.raise_tech_lesson04.entity.UserInfo;
import com.example.raise_tech_lesson04.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DemoController {
    @Autowired
    UserInfoMapper userInfoMapper;

    @RequestMapping
    public  String index(Model iModel){
        List<UserInfo> list = userInfoMapper.selectAll();
        iModel.addAttribute("userInfo", list);
        return "index";
    }
}
