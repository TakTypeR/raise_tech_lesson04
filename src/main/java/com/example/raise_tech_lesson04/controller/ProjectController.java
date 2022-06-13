/**
 * プロジェクト情報メインページの遷移管理
 */
package com.example.raise_tech_lesson04.controller;

import com.example.raise_tech_lesson04.entity.Project;
import com.example.raise_tech_lesson04.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ProjectController {

    //フィールドインジェクションではなくコンストラクタインジェクションが推奨される
    //https://pppurple.hatenablog.com/entry/2016/12/29/233141
    //@Autowired: 単一コンストラクタの場合、@Autowiredは不要
    //classsに@RequiredArgsConstructorをつければ、コンストラクタの定義は不要
    // 必須のメンバ（finalのメンバ）へ値をセットするための引数付きコンストラクタを自動生成する。
    private final ProjectService projectService;

    //ラムダ式ではスタック変数へは代入出来ないのでフィールド宣言
    String nextPage;

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
        //Optional: nullになる可能性があるインスタンスはOptionalを使う
        Optional<Project> p = projectService.getProject(id);

        nextPage ="project/project_edit";
        //ifPresentOrElse: if(!=null)..else..の代わり
        //nullの場合は、とりあえず遷移しない
        p.ifPresentOrElse(v -> model.addAttribute("project", p.get()), ()->nextPage="projects");
        //nullの場合のエラーハンドリングについて(参考)
        //Serviceクラスで例外のハンドリングをしてnullの場合は独自例外でエラーメッセージをフロントに返す構成にしていた。
        //レスポンスにエラーメッセージのJSONを乗せてフロントでエラーメッセージ’データがありません’的な物を表示させるようにしてました。
        //おそらく、404ページなどを表示させる場合はもっと深刻なエラーが発生した場合にして、
        //値がある無い程度のエラーならばメッセージをレスポンスに返して表示させるくらいの方がユーザビリティは良いかもしれないです

        return nextPage;
    }

    @PostMapping("/process_edit_project")
    public String processEditProject(@Validated @ModelAttribute("project")Project project,
                                     BindingResult result)
    {
        //エラーがある場合は、入力フォームへ戻る
        if(result.hasErrors())
        {
            return "project/project_edit";
        }

        //編集情報をDBへ反映
        projectService.updateProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/project/delete/{id}")
    public  String deleteProject(@PathVariable("id") int id)
    {
        //更新したDBで、同ページを再表示
        projectService.deleteProject(id);
        return "redirect:/projects";
    }

    /**
     * プロジェクトを登録ページへ遷移する
     * @param model ページのモデル情報
     * @return 新規登録ページ
     */
    @GetMapping("/new_project")
    public String newProject(Model model)
    {
        Project project = new Project();
        model.addAttribute("project", project);

        return "project/project_new";
    }

    @PostMapping("/process_new_project")
    public String processNewProject(@Validated @ModelAttribute("project") Project project, BindingResult result)
    {
        //@Validatedが設定されるので、エラーの場合はhasErrors()がtrue
        // エラーがある場合は、入力フォームページへ戻る
        if(result.hasErrors()){
            return "project/project_new";
        }

        projectService.insertProject(project);
        return "redirect:/projects";

    }

}
