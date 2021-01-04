package com.example.raise_tech_lesson04.controller;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.entity.UserInfo;
import com.example.raise_tech_lesson04.mapper.MachineInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MachinesController {

    //@Autowired合致するオブジェクトを探して自動生成してくれる
    @Autowired
    MachineInfoMapper machineInfoMapper;

    //機材リストメインページ
    @RequestMapping("/machines")
    public  String machines(Model iModel){
        //DBからデータ取得。mapper.xmlで関連付けられたSQLが呼ばれる
        List<MachineInfo> mList = machineInfoMapper.selectAll();
        //対応するresourceファイルから参照するために、値を登録
        iModel.addAttribute("machineInfo", mList);

        //SQLをコールしても良いが、上記のlistのサイズから分かるのでカット
        //int num = machineInfoMapper.numOfMachines();
        iModel.addAttribute("numOfMachines", mList.size());

        return "machines";
    }

    //machines.htmlのhrefに対する受け
    @GetMapping("/delete/{id}")
    // @PathVariable: URLに含まれるパラメータを取得する
    public String DeleteMachine(@PathVariable("id") int id)
    {
        //更新したＤＢで持って、同ページを再表示
        return "redirect:/machines";
    }
}
