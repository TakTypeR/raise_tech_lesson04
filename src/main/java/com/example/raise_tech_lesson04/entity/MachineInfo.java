package com.example.raise_tech_lesson04.entity;

//pom.xml::dependencyに以下を追加する
// <groupId>org.springframework.boot</groupId>
// <artifactId>spring-boot-starter-validation</artifactId>
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
    @GeneratedValue
    private int id;
    @NotBlank
    private String platform;
    //DBのcolumnに名前を合わせる
    @NotBlank
    private String host_name;
    @NotBlank
    private String owner;
}
