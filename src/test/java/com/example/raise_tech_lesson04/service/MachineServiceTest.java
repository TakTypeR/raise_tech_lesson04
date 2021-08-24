package com.example.raise_tech_lesson04.service;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


//
@SpringBootTest
public class MachineServiceTest {
    @Autowired
    MachineService machineService;

    @Test
    //メソッド名と実行結果はhtmlで出力されるので、メソッド名が日本語の方が結果を確認しやすい
    void MachineInfo_全件取得出来る_件数確認_正常() {
        List<MachineInfo> list = machineService.getAllMachines();
        assertThat(list.size(), not(0));   //0軒以上ある筈
        assertThat(list.size(), is(5));
    }

    @Test
    void MachineInfo_リストのプラットフォームとホスト名と所有者が取得できる_正常(){
        List<MachineInfo> list = machineService.getAllMachines();

        MachineInfo m = list.get(0);
        assertThat(m.getPlatform().getName(), is("WIN"));
        assertThat(m.getHost_name(), is("PC0002"));
        assertThat(m.getOwner(), is("鈴木"));

        m = list.get(1);
        assertThat(m.getPlatform().getName(), is("MAC"));
        assertThat(m.getHost_name(), is("PC0005"));
        assertThat(m.getOwner(), is("Mike"));

        m = list.get(4);
        assertThat(m.getPlatform().getName(), is("LINUX"));
        assertThat(m.getHost_name(), is("SV00001"));
        assertThat(m.getOwner(), is("IT-Jチーム"));
    }

}
