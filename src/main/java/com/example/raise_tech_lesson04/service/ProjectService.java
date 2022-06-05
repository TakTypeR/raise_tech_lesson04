package com.example.raise_tech_lesson04.service;

import com.example.raise_tech_lesson04.entity.Project;
import com.example.raise_tech_lesson04.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * プロジェクト情報に関するビジネスロジックを制御するクラス
 */
//更新系の処理を担当するクラスにはトランザクションを明示
@Transactional
@Service
public class ProjectService {

    private final ProjectMapper projectMapper;

    //フィールドインジェクションではなくコンストラクタインジェクションが推奨される
    //https://pppurple.hatenablog.com/entry/2016/12/29/233141
    //@Autowired: 単一コンストラクタの場合、@Autowiredは不要
    public ProjectService(ProjectMapper projectMapper){
        this.projectMapper = projectMapper;
    }

    public List<Project> getAllProjects()
    {
        return projectMapper.selectAll();
    }

    /**
     * 指定IDのプロジェクトを取得
     */
    public Optional<Project> getProject(int id) { return projectMapper.findById(id); }

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

    /**
     * プロジェクト情報を登録
     * @param Project 追加するプロジェクト情報
     */
    public int insertProject(Project project)
    {
        projectMapper.insert(project);
        return numOfProject();
    }


    public int numOfProject(){ return projectMapper.numOfProjects(); }
}
