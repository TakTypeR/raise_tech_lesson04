package com.example.raise_tech_lesson04.service;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.mapper.MachineInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 機材情報に関するビジネスロジックを制御するクラス
 */
@Service
public class MachineService {
    @Autowired
    private MachineInfoMapper machineInfoMapper;

    /**
     * 全機材情報の取得
     */
    public List<MachineInfo> getAllMachines() {
        //DBからデータ取得。mapper.xmlで関連付けられたSQLが呼ばれる
        return machineInfoMapper.selectAll();
    }

    /**
     * 指定IDの機材を取得
     */
    public MachineInfo getMachine(int id) {
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
    public void updateMachine( MachineInfo machineInfo ) {
        machineInfoMapper.update(machineInfo);
    }

    /**
     * 機材情報を登録
     * @param machineInfo 追加する機材情報
     */
    public void insertMachine( MachineInfo machineInfo ) {
        machineInfoMapper.insert(machineInfo);
    }

    /**
     * 登録されている機材情報数の取得
     * @return 登録された機材情報数
     */
    public int numOfMachines() {
        return machineInfoMapper.numOfMachines();
    }

}
