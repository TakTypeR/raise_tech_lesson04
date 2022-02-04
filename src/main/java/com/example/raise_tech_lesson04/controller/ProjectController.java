/**
 * プロジェクト情報メインページの遷移管理
 */
package com.example.raise_tech_lesson04.controller;

import com.example.raise_tech_lesson04.entity.Project;
import com.example.raise_tech_lesson04.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * プロジェクトリストメインページを表示する
     * @param iModel プロジェクトリストデータを管理するモデル
     * @return 対応ページhtmlファイル名
     */
    @RequestMapping("/projects")
    public String projects(Model iModel)
    {
        //DBから取得したデータを対応するリソースに渡すためにアトリビュートを設定
        List<Project> plist = projectService.getAllProjects();
        iModel.addAttribute("projects", plist);

        iModel.addAttribute("numOfProjects", plist.size());

        return "projects";
    }

    @GetMapping("/project/edit/{id}")
    public String editProject(@PathVariable("id") int id, Model model)
    {
        return "project/project_edit";
    }

    @GetMapping("/project/delete/{id}")
    public  String deleteProject(@PathVariable("id") int id)
    {
        //更新したDBで、同ページを再表示
        projectService.deleteProject(id);
        return "redirect:/projects";
    }

}
