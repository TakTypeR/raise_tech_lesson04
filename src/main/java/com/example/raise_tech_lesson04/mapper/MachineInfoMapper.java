package com.example.raise_tech_lesson04.mapper;

import java.util.List;
import com.example.raise_tech_lesson04.entity.MachineInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 機材座用法DBにアクセスする為のJava側のインターフェースクラス<br>
 * {@code @Mapper} アノテーションを付与したインターフェースに xml で定義した SQL が対応づけられる<br>
 * 該当のメソッドを実行した時の SQL は XML で定義する<br>
 * <p>Note: XMLはresourceフォルダ以下で、interfaceクラスがあるフォルダパスと合わせる</p>
 */
@Mapper
public interface MachineInfoMapper {

    /**
     * 全機材情報の取得
     * <p>Note: メソッド名はmapper.xmlのidと一致している事</p>
     * @return 機材情報リスト
     */
    List<MachineInfo> selectAll();

    /**
     * 指定IDの機材情報を検索する
     * @param id 検索対象の機材情報ID
     * @return 機材情報
     */
    MachineInfo findById( int id );
    MachineInfo findById2( int id );

    /**
     * 指定IDの機材情報を削除する
     * @param id 削除対象の機材情報ID
     */
    void deleteById( int id );

    /**
     * 機材情報の更新<br>
     * 入力された機材情報が持つIDに対応した機材情報を更新する
     * @param machineInfo 更新する機材情報
     */
    void update( MachineInfo machineInfo );

    /**
     * 機材情報をDBへ追加
     * @param machineInfo 追加する機材情報
     */
    void insert( MachineInfo machineInfo );

    /**
     * DBに登録されている機材情報数の取得
     * @return 登録された機材情報数
     */
    int numOfMachines();
}
