package com.example.raise_tech_lesson04.mapper;

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
     * 指定IDのプロジェクト情報を検索する
     * @param id 検索対象の機材情報ID
     * @return プロジェクト情報
     */
    Project findById(int id);

    /**
     * 指定IDの機材情報を削除する
     * @param id 削除対象の機材情報ID
     */
    void deleteById(int id);

    /**
     * プロジェクト情報の更新<br>
     * 入力された情報が持つIDに対応した情報を更新する
     * @param Project 更新するプロジェクト情報
     */
    void update(Project project);

    /**
     * プロジェクト情報をDBへ追加
     * @param Project 追加するプロジェクト情報
     */
    void insert(Project project);

    /**
     * DBに登録されているプロジェクト情報数の取得
     * @return 登録された機材情報数
     */
    int numOfProjects();
}
