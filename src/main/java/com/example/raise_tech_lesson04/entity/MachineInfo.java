package com.example.raise_tech_lesson04.entity;

import lombok.Data;

@Data
public class MachineInfo {
    private String id;
    private String platform;
    //DBのcolumnに名前を合わせる
    private String host_name;
    private String owner;
}
