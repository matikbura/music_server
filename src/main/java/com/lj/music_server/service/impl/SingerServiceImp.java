package com.lj.music_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lj.music_server.entity.MyCollection;
import com.lj.music_server.entity.Singer;
import com.lj.music_server.entity.SingerLanguage;
import com.lj.music_server.entity.SingerType;
import com.lj.music_server.enums.TypeEnum;
import com.lj.music_server.mapper.MyCollectionMapper;
import com.lj.music_server.mapper.SingerMapper;
import com.lj.music_server.service.ISingerService;
import com.lj.music_server.utils.ParseUtils;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.SingerVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingerServiceImp implements ISingerService {
    final SingerMapper singerMapper;
    final MyCollectionMapper myCollectionMapper;

    public SingerServiceImp(SingerMapper singerMapper, MyCollectionMapper myCollectionMapper) {
        this.singerMapper = singerMapper;
        this.myCollectionMapper = myCollectionMapper;
    }

    @Override
    public ArrayList<SingerVO> getSinger(SingerVO singer) {
        ArrayList<SingerVO> singers = new ArrayList<>();
        ArrayList<Singer> singerList = singerMapper.getSinger(singer);
        for (Singer singer1 : singerList) {
            singers.add((ParseUtils.po_parse_vo(singer1, SingerVO.class)));
        }
        return singers;
    }

    @Override
    public void addSinger(SingerVO singerVO) {
        singerMapper.addSinger(singerVO);
    }

    @Override
    public ArrayList<SingerVO> top(Integer limit) {
        return ParseUtils.po_parse_vo(singerMapper.top(limit), SingerVO.class);
    }

    @Override
    public void getSingerDetailPage(Page<SingerVO> page) {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        ArrayList<Singer> singers = singerMapper.getSingerDetail(page.getCondition());
        PageInfo<Singer> pageInfo = new PageInfo<>(singers);
        page.setTotal(pageInfo.getTotal());
        List<Singer> list = pageInfo.getList();
        ArrayList<SingerVO> singerVOS = ParseUtils.po_parse_vo(list, SingerVO.class);

        for (SingerVO vo : singerVOS) {

            vo.setSingerType(singerMapper.getSingerTypeById(vo.getSingerTypeId()));
            vo.setSingerLanguage(singerMapper.getSingerLanguageById(vo.getSingerLanguageId()));


        }
        page.setData(singerVOS);
    }

    @Override
    public void updateSinger(SingerVO singerVO) {
        singerMapper.updateSinger(singerVO);
    }

    @Override
    public void deleteSinger(Integer id) {
        singerMapper.deleteSinger(id);
    }

    @Override
    public ArrayList<SingerType> getSingerType() {
        return singerMapper.getSingerType();
    }

    @Override
    public ArrayList<SingerLanguage> getSingerLanguage() {
        return singerMapper.getSingerLanguage();
    }

    @Override
    public void getSingerConditionPage(Page<SingerVO> page) {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        ArrayList<Singer> singers = singerMapper.getSinger(page.getCondition());
        PageInfo<Singer> pageInfo = new PageInfo<>(singers);
        page.setTotal(pageInfo.getTotal());
        List<Singer> list = pageInfo.getList();
        ArrayList<SingerVO> singerVOS = ParseUtils.po_parse_vo(list, SingerVO.class);
        if (page.getCondition().getLoginId() != null) {
            MyCollection myCollection;
            for (SingerVO vo : singerVOS) {
                vo.setSingerType(singerMapper.getSingerTypeById(vo.getSingerTypeId()));
                vo.setSingerLanguage(singerMapper.getSingerLanguageById(vo.getSingerLanguageId()));
                myCollection = new MyCollection();
                myCollection.setType(3);
                myCollection.setUserId(page.getCondition().getLoginId());
                myCollection.setTypeId(vo.getId());
                vo.setIsCollected(myCollectionMapper.getMyCollection(myCollection).size() > 0);
            }
        } else {
            for (SingerVO vo : singerVOS) {
                vo.setSingerType(singerMapper.getSingerTypeById(vo.getSingerTypeId()));
                vo.setSingerLanguage(singerMapper.getSingerLanguageById(vo.getSingerLanguageId()));
            }
        }
        page.setHasNext(pageInfo.isHasNextPage());
        page.setData(singerVOS);
    }

    @Override
    public SingerVO getSingerById(Integer id, Integer loginId) {
        SingerVO singerVO = ParseUtils.po_parse_vo(singerMapper.getSingerById(id), SingerVO.class);
        singerVO.setSingerType(singerMapper.getSingerTypeById(singerVO.getSingerTypeId()));
        if (loginId != null) {
            MyCollection myCollection = new MyCollection();
            myCollection.setUserId(loginId);
            myCollection.setTypeId(id);
            myCollection.setType(TypeEnum.SINGER.getTypeNum());
            singerVO.setIsCollected(myCollectionMapper.getMyCollection(myCollection).size() > 0);
        }
        return singerVO;
    }

}
