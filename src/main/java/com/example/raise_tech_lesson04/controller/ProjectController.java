/**
 * プロジェクト情報メインページの遷移管理
 */
package com.example.raise_tech_lesson04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

    /**
     * プロジェクトリストメインページを表示する
     * @param iModel プロジェクトリストデータを管理するモデル
     * @return 対応ページhtmlファイル名
     */
    @RequestMapping("/projects")
    public String projects(Model iModel){
        return "projects";
    }

}
