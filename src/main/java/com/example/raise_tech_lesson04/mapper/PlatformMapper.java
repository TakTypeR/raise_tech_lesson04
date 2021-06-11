package com.example.raise_tech_lesson04.mapper;

import java.util.List;
import com.example.raise_tech_lesson04.entity.Platform;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformMapper {

    /**
     * 全プラットフォーム情報をDBから取得
     * <p>Note: メソッド名はmapper.xmlのidと一致している事</p>     *
     * @return プラットフォーム情報
     */
    List<Platform> selectAll();
}
