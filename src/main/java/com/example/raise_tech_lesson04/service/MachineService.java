package com.example.raise_tech_lesson04.service;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.mapper.MachineInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 機材情報に関するビジネスロジックを制御するクラス
 */
//更新系の処理を担当するクラスにはトランザクションを明示
@Transactional
@Service
public class MachineService {
    private final MachineInfoMapper machineInfoMapper;

    //フィールドインジェクションではなくコンストラクタインジェクションが推奨される
    //https://pppurple.hatenablog.com/entry/2016/12/29/233141
    //@Autowired: 単一コンストラクタの場合、@Autowiredは不要
    //classsに@RequiredArgsConstructorをつければ、コンストラクタの定義は不要
    // 必須のメンバ（finalのメンバ）へ値をセットするための引数付きコンストラクタを自動生成する。
    public MachineService(MachineInfoMapper machineInfoMapper){
        this.machineInfoMapper = machineInfoMapper;
    }

    /**
     * 全機材情報の取得
     */
    public List<MachineInfo> getAllMachines() {
        //DBからデータ取得。mapper.xmlで関連付けられたSQLが呼ばれる
        return machineInfoMapper.selectAll();
    }

    /**
     * 指定IDの機材を取得
     * @param id 機材ID
     * @return 対応する機材情報
     */
    public Optional<MachineInfo> getMachine(int id) {
        return machineInfoMapper.findById(id);
    }

    /**
     * 指定IDの機材を削除
     * @return 削除後のDBのレコード数
     */
    public int deleteMachine(int id) {
        machineInfoMapper.deleteById(id);
        return numOfMachines();
    }

    /**
     * 機材情報の更新<br>
     * 入力された機材情報が持つIDに対応した機材情報を更新する
     * @param machineInfo 更新する機材情報
     */
    public void updateMachine(MachineInfo machineInfo) {
        machineInfoMapper.update(machineInfo);
    }

    /**
     * 機材情報を登録
     * @param machineInfo 追加する機材情報
     */
    public int insertMachine(MachineInfo machineInfo) {
        machineInfoMapper.insert(machineInfo);
        return numOfMachines();
    }

    /**
     * 登録されている機材情報数の取得
     * @return 登録された機材情報数
     */
    public int numOfMachines() {
        return machineInfoMapper.numOfMachines();
    }

}
