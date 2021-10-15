package com.example.raise_tech_lesson04.service;

import com.example.raise_tech_lesson04.entity.MachineInfo;
import com.example.raise_tech_lesson04.entity.Platform;
import com.example.raise_tech_lesson04.mapper.MachineInfoMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;


//JUnit5ベース

//テストクラス作成方法/自動作成でなく手動で作成
//1,test以下にpackage作成(テスト対象クラスと同じパス)
//2.test classを通常classの方法で作成
//3.クラスに@SpringBootTestを追加
@SpringBootTest
public class MachineServiceTest {
    @Mock
    //本番DBのデータを使いたくない時、@Mockで疑似のインスタンスを生成してテストする
    //これをMachineServiceから呼ぶ
    MachineInfoMapper machineInfoMapper;

    //Mockを使う場合@Autowired->@InjectMocksへ変更
    //test target
    @InjectMocks
    MachineService machineServiceMock;

    //DBを使う時は@Autowired
    @Autowired
    MachineService machineService;

    //@BeforeAll::テスト(@Test)前に行う準備処理(static method)を記述(@Before @JUnit4)
    //全テスト前に1回だけ呼ばれる
    @BeforeAll
    public static  void setupAll(){
    }

    //TODO: Serviceにある全メソッドをテストする

    /**
     * getAllMachines()のテスト
     */
    //@Test::以下のメソッドをテスト対象として登録
    @Test
    //メソッド名と実行結果はhtmlで出力されるので、メソッド名が日本語の方が結果を確認しやすい
    void MachineInfo_全件取得出来る_件数確認_正常() {
        //Mockを使ったテスト:準備
        List<MachineInfo> ret = new ArrayList<MachineInfo>();
        MachineInfo m = new MachineInfo();
        ret.add(m);
        //when: selectAll()がコールされたら theReturn: retを返す
        when(machineInfoMapper.selectAll()).thenReturn(ret);

        List<MachineInfo> list = machineServiceMock.getAllMachines();
        assertThat(list.size(), not(0));   //0軒以上ある筈
        //mockの場合は追加したのは1件なので1の筈
        assertThat(list.size(), is(1));
    }   //assertThat(list.size(), is(5));

    /**
     * getMachine()テスト
     */
    @Test
    void MachineInfo_指定IDの機材情報を取得できる_正常(){

        MachineInfo m = new MachineInfo(new Platform(10, "WIN"));
        m.setId(10); m.setHost_name("PC0002"); m.setOwner("鈴木");
        when(machineInfoMapper.findById(10)).thenReturn(m);

        m = machineServiceMock.getMachine(10);
        //色々な検証メソッドの例
        assertThat(m.getPlatformName(), is("WIN"));
        assertThat(m.getHost_name(), equalTo("PC0002"));
        assertThat(m, hasProperty("owner", equalTo("鈴木")));
    }

    /**
     * deleteMachine()テスト
     */
    //@Disabled: テスト実行を無効化(@Ignoreは@JUnit4版)
    //@Disabled
    @Test
    void MachineInfo_機材情報の削除_正常(){
        //DB先頭の機材を削除
        List<MachineInfo> machines = machineService.getAllMachines();
        int size = machines.size();
        if(size > 0) {
            //DBのレコード数、順番が変わらない様に最後の要素でテストする
            int numOfMachineBefore = machineService.numOfMachines();
            MachineInfo m = machines.get(size-1);
            int numOfMachineAfter = machineService.deleteMachine(m.getId());
            assertThat(numOfMachineBefore-numOfMachineAfter, is(1));

            //deleteされた後もmの値は有効
            machineService.insertMachine(m);
        }
    }

    //@Disabled: テスト実行を無効化(@Ignoreは@JUnit4版)
    @Disabled
    @Test
    void MachineInfo_リストのプラットフォームとホスト名と所有者が取得できる_正常(){
        //DBの値を参照するのでMockじゃないServiceをコール
        List<MachineInfo> list = machineService.getAllMachines();

        MachineInfo m = list.get(0);
        assertThat(m.getPlatform().getName(), is("WIN"));
        assertThat(m.getHost_name(), is("PC0001"));
        assertThat(m.getOwner(), is("鈴木"));

        m = list.get(1);
        assertThat(m.getPlatform().getName(), is("MAC"));
        assertThat(m.getHost_name(), is("PC0002"));
        assertThat(m.getOwner(), is("大谷"));
    }

}
