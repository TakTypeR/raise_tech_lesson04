/**
 * 機材情報ページの遷移管理
 * @author Takahsia Suzuki
 */

package com.example.raise_tech_lesson04.controller;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.entity.Platform;
import com.example.raise_tech_lesson04.mapper.MachineInfoMapper;
import com.example.raise_tech_lesson04.mapper.PlatformMapper;
import com.example.raise_tech_lesson04.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.crypto.Mac;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 機材情報ページの遷移を管理するクラス
 */
@RequiredArgsConstructor
@Controller
public class MachinesController {

    //@Autowired合致するオブジェクトを探して自動生成してくれる
    @Autowired
    MachineService machineService;
    @Autowired
    PlatformMapper platformMapper;

    /**
     * 機材リストメインページを表示する
     * @param iModel 機材リストデータを管理するモデル
     * @return 対応ページhtmlファイル名
     */
    @RequestMapping("/machines")
    public String machines(Model iModel){
        //DBからデータ取得。mapper.xmlで関連付けられたSQLが呼ばれる
        List<MachineInfo> mList = machineService.getAllMachines();
        //対応するresourceファイルから参照するために、値を登録
        iModel.addAttribute("machineInfo", mList);

        //SQLをコールしても良いが、上記のlistのサイズから分かるのでカット
        //int num = machineService.numOfMachines();
        iModel.addAttribute("numOfMachines", mList.size());

        return "machines";
    }

    /**
     * 指定した機材を機材リストから削除する<br>
     * machines.htmlのdelete::hrefに対する受け
     * {@code @PathVariable}: URLに含まれるパラメータを取得する
     * @param id 削除対象の機材情報ID
     * @return 削除後の遷移先機材リストページ
     */
    @GetMapping("/delete/{id}")
    public String deleteMachine(@PathVariable("id") int id)
    {
        //更新したＤＢで持って、同ページを再表示
        machineService.deleteMachine(id);
        return "redirect:/machines";
    }

    /**
     * 指定した機材情報を更新する為に、情報を表示する<br>
     * machines.htmlのedit::hrefに対する受け
     * @param id 更新対象の機材情報ID
     * @param model 機材リストデータを管理するモデル
     * @return 更新ページ名
     */
    @GetMapping("/edit/{id}")
    public String editMachine(@PathVariable("id") int id, Model model)
    {
        //プラットフォーム情報のプルダウンメニューを表示する為、viewへ渡す
        model.addAttribute( "platformItems", getPlatformItems() );
        //遷移先のページで機材情報を表示する為、機材情報を取得して渡す
        MachineInfo m = machineService.getMachine(id);
        model.addAttribute("machine", m);

        return "machine/machine_edit";
    }

    /**
     * 機材情報更新決定後に除法をDBへ反映する<br>
     * machine_edit.htmlのth:actionに対する受け<br>
     * {@code @Validated}:
     * このメソッドが呼ばれる前に、Entityクラスに付けたバリエーションチェックが行われる<br>
     * エラーがある場合は、引数のBindingResultにエラーが渡される<br>
     * {@code @ModelAttribute}: モデルへ渡され。html側で属性名として${}で参照可能<br>
     *  defaultでクラス名の頭文字を小文字にしたものが属性名<br>
     * ＊型名がバインディング名と同じななら、@ModelAttribute MachineInfo mach<br>
     * 今回は異なるため@ModelAttribute("machine") MachineInfo machineと明示するする必要がある。<br>
     *  これと等価: model.addAttribute("machine", machine)<br>
     * @param machine 更新する機材情報
     * @param result MachineInfo classの評価アノテーションに基づいた入力値のチェック結果
     * @return 遷移先の機材リストページ
     */
    @PostMapping("/process_edit_machine")
    public String processEditMachine(@Validated @ModelAttribute("machine") MachineInfo machine, BindingResult result)
    {
        //@Validatedが設定されるので、エラーの場合はhasErrors()がtrue
        // エラーがある場合は、入力フォームページへ戻る
        if(result.hasErrors()){
            return "machine/machine_edit";
        }

        //フォームセレクトではid情報しか設定されないのでplatformNameを設定
        Platform platform = platformMapper.findById(machine.getPlatform().getId());
        machine.setPlatform(platform);

        machineService.updateMachine(machine);
        return "redirect:/machines";
    }

    /**
     * 機材を新規登録するページへ遷移する
     * @param model　ページのモデル情報
     * @return 新規登録ページ名
     */
    @GetMapping("/new_machine")
    public String newMachine(Model model)
    //public String newMachine(@ModelAttribute("machine") MachineInfo machine, Model model)
    {
        //デフォルトコンストラクタだとプラットフォーム情報がnull。platformインスタンスを確保してから設定する
        //コンストラクタ内でplatformをnewするとメモリ確保の制御が出来ないので、外から与える
        MachineInfo machine = new MachineInfo(new Platform());
        model.addAttribute("machine", machine);

        //プラットフォーム情報のプルダウンメニューを表示する為、viewへ渡す
        model.addAttribute("platformItems", getPlatformItems());
        return "machine/machine_new";
    }

    /**
     *
     * @param machine 新規登録する機材情報
     * @param result MachineInfo classの評価アノテーションに基づいた入力値のチェック結果
     * @return 遷移先の機材リストページ
     */
    @PostMapping("/process_new_machine")
    public String processNewMachine(@Validated @ModelAttribute("machine") MachineInfo machine, BindingResult result)
    {
        //@Validatedが設定されるので、エラーの場合はhasErrors()がtrue
        // エラーがある場合は、入力フォームページへ戻る
        if(result.hasErrors()){
            return "machine/machine_new";
        }

        machineService.insertMachine(machine);
        return "redirect:/machines";
    }

    /**
     * プラットフォーム情報取得
     * @return DB情のIDと名前の文字列のリスト
     */
    private  List<Platform> getPlatformItems()
    {
        return platformMapper.selectAll();
    }
}
