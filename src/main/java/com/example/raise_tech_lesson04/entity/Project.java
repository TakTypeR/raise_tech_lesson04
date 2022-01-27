package com.example.raise_tech_lesson04.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * プロジェクト管理クラス
 * プロジェクトの様々な情報を扱うクラス
 */
@Data
public class Project {
    /**
     * Constructors
     */
    public Project(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "プロジェクト名を入れて下さい")
    @Size(max = 128)
    private String name;
}
