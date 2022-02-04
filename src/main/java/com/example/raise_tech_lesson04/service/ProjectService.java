package com.example.raise_tech_lesson04.service;

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
     * 指定IDのプロジェクトを削除する
     * @param id 削除するプロジェクトID
     * @return 削除後のDBのレコード数
     */
    public int deleteProject(int id)
    {
        projectMapper.deleteById(id);
        return numOfProject();
    }

    public int numOfProject(){ return projectMapper.numOfProjects(); }
}
