package com.lj.music_server.service;

import com.lj.music_server.entity.Singer;
import com.lj.music_server.entity.SingerLanguage;
import com.lj.music_server.entity.SingerType;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.SingerVO;

import java.util.ArrayList;

public interface ISingerService {
    ArrayList<SingerVO> getSinger(SingerVO singer);

    void addSinger(SingerVO singerVO);

    ArrayList<SingerVO> top(Integer limit);

    void getSingerDetailPage(Page<SingerVO> page);

    void updateSinger(SingerVO singerVO);

    void deleteSinger(Integer id);

    ArrayList<SingerType> getSingerType();

    ArrayList<SingerLanguage> getSingerLanguage();

    void getSingerConditionPage(Page<SingerVO> page);

    Singer getSingerById(Integer id, Integer loginId);
}
