package com.example.raise_tech_lesson04.service;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.entity.Project;
import com.example.raise_tech_lesson04.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * プロジェクト情報に関するビジネスロジックを制御するクラス
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> getAllProjects()
    {
        return projectMapper.selectAll();
    }

    /**
     * 指定IDのプロジェクトを取得
     */
    public Project getProject(int id) { return projectMapper.findById(id); }

    /**
     * 指定IDのプロジェクトを削除する
     * @param id 削除するプロジェクトID
     * @return 削除後のDBのレコード数
     */
    public int deleteProject(int id)
    {
        projectMapper.deleteById(id);
        return numOfProject();
    }

    /**
     * プロジェクト情報の更新<br>
     * 入力された情報が持つIDに対応した情報を更新する
     * @param Project 更新するプロジェクト情報
     */
    public void updateProject(Project project) { projectMapper.update(project); }



    public int numOfProject(){ return projectMapper.numOfProjects(); }
}
