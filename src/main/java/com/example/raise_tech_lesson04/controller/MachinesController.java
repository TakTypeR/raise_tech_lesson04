package com.example.raise_tech_lesson04.controller;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.entity.UserInfo;
import com.example.raise_tech_lesson04.mapper.MachineInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.crypto.Mac;
import java.util.List;

@RequiredArgsConstructor
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

    //machines.htmlのdelete::hrefに対する受け
    @GetMapping("/delete/{id}")
    // @PathVariable: URLに含まれるパラメータを取得する
    public String deleteMachine(@PathVariable("id") int id)
    {
        //更新したＤＢで持って、同ページを再表示
        machineInfoMapper.deleteById(id);
        return "redirect:/machines";
    }

    //machines.htmlのedit::hrefに対する受け
    @GetMapping("/edit/{id}")
    public String editMachine(@PathVariable("id") int id, Model model)
    {
        //遷移先のページで機材情報を表示する為、機材情報を取得して渡す
        MachineInfo m = machineInfoMapper.findById(id);
        model.addAttribute("machine", m);

        return "machine/machine_edit";
    }

    @GetMapping("/new_machine")
    public String newMachine(@ModelAttribute("machine") MachineInfo machine)
    {
        return "machine/machine_new";
    }

    //machine_edit.htmlのth:actionに対する受け
    //@Validated:
    //このメソッドが呼ばれる前に、Entityクラスに付けたバリエーションチェックが行われる
    //エラーがあるばあいは、引数のBindingResultにエラーが渡される
    //@ModelAttribute: モデルへ渡され。html側で属性名として${}で参照可能
    // defaultでクラス名の頭文字を小文字にしたものが属性名
    //＊型名がバインディング名と同じななら、@ModelAttribute MachineInfo machine
    //今回は異なるため@ModelAttribute("machine") MachineInfo machineと明示するする必要がある。
    // これと等価: model.addAttribute("machine", machine)
    @PostMapping("/process_edit_machine")
    public String processEditMachine(@Validated @ModelAttribute("machine") MachineInfo machine, BindingResult result)
    {
        //@Validatedが設定されるので、エラーの場合はhasErrors()がtrue
        // エラーがある場合は、入力フォームページへ戻る
        if(result.hasErrors()){
            return "machine/machine_edit";
        }

        machineInfoMapper.update(machine);;
        return "redirect:/machines";
    }
}
