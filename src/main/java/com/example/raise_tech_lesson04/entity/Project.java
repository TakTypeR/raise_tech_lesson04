package com.example.raise_tech_lesson04.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * プロジェクト管理クラス
 * プロジェクトの様々な情報を扱うクラス
 */
@Data
public class Project {

    /**
     * DBのcolumnに名前を合わせる
     * {@code @GeneratedValue}: 主キーの値を自動採番する. @Idを一緒に使う
     * <p><b>Note: DB側でAuto Incrementを有効にする事。そうしないと、新規追加時にidエラーになる
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * プロジェクト名
     */
    @NotBlank(message = "プロジェクト名を入れて下さい")
    @Size(max = 128)
    private String name;

    /**
     * このプロジェクトに所属するPCのリスト
     */
    List<MachineInfo> machines;

}
