package com.example.raise_tech_lesson04.mapper;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    /**
     * 全機材情報の取得     *
     * <p>Note: メソッド名はmapper.xmlのidと一致している事</p>     *
     * @return 機材情報リスト
     */
    List<Project> selectAll();

    /**
     * DBに登録されているプロジェクト情報数の取得
     * @return 登録された機材情報数
     */
    int numOfProjects();
}
