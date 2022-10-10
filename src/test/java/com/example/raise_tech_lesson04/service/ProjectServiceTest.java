package com.example.raise_tech_lesson04.service;

import com.example.raise_tech_lesson04.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    ProjectService projectService;

    @Test
    void ProjectService_プロジェクト情報を全件取得出来る_正常() {
        List<Project> plist = projectService.getAllProjects();
        //期待検証がメソッドチェーンで行えるので、いちいちメソッド名を覚えなくて良い
        // https://qiita.com/gengogo5/items/317b6f260b6fecc184fc
        assertThat(plist.size()).isEqualTo(3);
        Project p = plist.get(0);
        assertThat(p.getName()).isEqualTo("test-Project0");
        assertThat(p.getMachines().size()).isEqualTo(1);
    }
}
