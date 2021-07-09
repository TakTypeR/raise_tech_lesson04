package com.example.raise_tech_lesson04.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 機材のOS情報を管理するクラス
 */
@Entity
@Data
public class Platform {
    /**
     * DB::platformのid情報
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;

    /**
     * 実際のOS名(Win, Mac....)
     * select menu等で情報を取得する為に必要
     */
    @NotBlank
    @Size(max=24)
    private String name="UNKNOWN";

    public Platform(){}
}
