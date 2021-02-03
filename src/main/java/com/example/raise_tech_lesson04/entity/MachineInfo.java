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


import lombok.Data;

//@Data:クラスメンバに対してsetter/getterを自動追加
@Data
public class MachineInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "OS名を入力して下さい")
    private String platform;

    //DBのcolumnに名前を合わせる
    @NotBlank(message = "マシンのホスト名を入力して下さい")
    private String host_name;

    @NotBlank(message = "マシンの所有者を入力して下さい")
    private String owner;
}
