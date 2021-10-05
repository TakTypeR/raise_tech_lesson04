package com.example.raise_tech_lesson04.entity;

//pom.xml::dependencyに以下を追加する
// <groupId>org.springframework.boot</groupId>
// <artifactId>spring-boot-starter-validation</artifactId>
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;

//pom.xml::dependencyに以下を追加する
// <groupId>org.springframework.boot</groupId>
// <artifactId>spring-boot-starter-data-jpa</artifactId>
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.example.raise_tech_lesson04.entity.Platform;

import lombok.Data;

//参考
//classに@Entity(JPA)(@javax.persistence.Entity )を付けると、
// 作成したクラスをデータベースに書き込む1件のレコードとして定義することが出来る
//参考URL
//https://builder.japan.zdnet.com/sp_oracle/35067018/
//http://itref.fc2web.com/java/jpa/annotation.html

//@Data:クラスメンバに対してsetter/getterを自動追加

/**
 * 機材情報管理クラス
 */
@Data
public class MachineInfo {

    /**
     * Constructors
     */
    public MachineInfo(){}
    public MachineInfo(int id, String hostName, String owner, Platform platform)
    {
        setId(id);
        setHost_name(hostName);
        setOwner(owner);
        setPlatform(platform);
    }
    /**
     * Platform情報を外から与えるコンストラクタ
     * @param platform 機材のプラットフォーム情報 not {@code null}
     */
    public MachineInfo(Platform platform)
    {
        setPlatform(platform);
    }

    /**
     * プラットフォーム文字列を取得
     */
    public String getPlatformName(){ return platform.getName(); }

    /**
     * DBのcolumnに名前を合わせる
     * {@code @GeneratedValue}: 主キーの値を自動採番する. @Idを一緒に使う
     * <p><b>Note: DB側でAuto Incrementを有効にする事。そうしないと、新規追加時にidエラーになる
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * OS種別(テーブル参照)：Win/Mac/Linux
     * idを保持するだけでもＯＫだが、ページで表示する時には名前を保持している方が良いのでメンバとして残す
     */
    private Platform platform;

    /**
     * 一意に与えられたホスト名
     * <p><b>DBのcolumnに名前を合わせる
     */
    @NotBlank(message = "機材のホスト名を入力して下さい")
    @Size(max=128)
    private String host_name;

    /**
     * 主な所有者
     */
    @NotBlank(message = "機材の所有者を入力して下さい")
    @Size(max=128)
    private String owner;
}
