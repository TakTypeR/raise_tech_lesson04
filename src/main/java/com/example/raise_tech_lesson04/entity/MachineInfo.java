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


import lombok.Data;

//参考
//classに@Entity(JPA)(@javax.persistence.Entity )を付けると、
// 作成したクラスをデータベースに書き込む1件のレコードとして定義することが出来る
//参考URL
//https://builder.japan.zdnet.com/sp_oracle/35067018/
//http://itref.fc2web.com/java/jpa/annotation.html

//@Data:クラスメンバに対してsetter/getterを自動追加
@Data
public class MachineInfo {
    //DBのcolumnに名前を合わせる
    //@GeneratedValue: 主キーの値を自動採番する. @Idを一緒に使う
    //****DB側でAuto Incrementを有効にする事。そうしないと、新規追加時にidエラーになる
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "OS名を入力して下さい")
    @Size(max=24)
    private String platform;

    //DBのcolumnに名前を合わせる
    @NotBlank(message = "機材のホスト名を入力して下さい")
    @Size(max=128)
    private String host_name;

    @NotBlank(message = "機材の所有者を入力して下さい")
    @Size(max=128)
    private String owner;
}
