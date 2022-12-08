package com.lj.music_server.mapper;

import com.lj.music_server.entity.Singer;
import com.lj.music_server.entity.SingerLanguage;
import com.lj.music_server.entity.SingerType;
import com.lj.music_server.vo.SingerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SingerMapper {
    ArrayList<Singer> getSinger(Singer singer);

    ArrayList<Singer> getSingerByMusicId(Integer id);
    Singer getSingerDetailById(Integer id);
    void addSinger(SingerVO singerVO);
    void updateSinger(SingerVO singerVO);

    ArrayList<Singer> top(Integer limit);

    ArrayList<Singer> getSingerDetail(SingerVO condition);

    void deleteSinger(Integer id);

    Singer getSingerById(Integer singerId);

    ArrayList<SingerType> getSingerType();

    SingerType getSingerTypeById(Integer id);

    ArrayList<SingerLanguage> getSingerLanguage();

    SingerLanguage getSingerLanguageById(Integer singerLanguageId);

    void addClickCount(Integer typeId);
}
