package com.example.raise_tech_lesson04.mapper;

import java.util.List;
import com.example.raise_tech_lesson04.entity.MachineInfo;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
// アノテーションを付与したインターフェースに xml で定義した SQL が対応づけられる
// 該当のメソッドを実行した時の SQL は XML で定義する
//XMLはresourceフォルダ以下で、interfaceクラスがあるフォルダパスと合わせる
@Mapper
public interface MachineInfoMapper {
    //メソッド名はmapper.xmlのidと一致している事
    List<MachineInfo> selectAll();
    MachineInfo findById( int id );
    void deleteById( int id );
    void update( MachineInfo machineInfo );
    void insert( MachineInfo machineInfo );

    int numOfMachines();
}
