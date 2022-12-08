package com.lj.music_server.controller;

import cn.hutool.core.util.StrUtil;
import com.lj.music_server.annotation.NotAuthorization;
import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.Result;
import com.lj.music_server.service.ISingerService;
import com.lj.music_server.vo.SingerVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("singer")
public class SingerController {
    final
    ISingerService singerService;

    public SingerController(ISingerService singerService) {
        this.singerService = singerService;
    }
    @NotAuthorization
    @RequestMapping("getSinger")
    public Result<Object> getSinger(SingerVO singer) {
        return new Result<>(true,HttpStatusEnum.OK, singerService.getSinger(singer));
    }

    @NotAuthorization
    @RequestMapping("top")
    public Result<Object> top(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        return new Result<>(true,HttpStatusEnum.OK, singerService.top(limit));
    }

    @NotAuthorization
    @RequestMapping("getSingerDetailPage")
    public Result<Object> getSingerDetailPage(@RequestBody Page<SingerVO> page) {
        singerService.getSingerDetailPage(page);
        return new Result<>(true,HttpStatusEnum.OK,page);
    }

    @RequestMapping("addSinger")
    public Result<Object> addSinger(@RequestBody SingerVO singerVO) {
        //参数校验
        if (singerVO.getName() == null || singerVO.getName().equals("")) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, "歌手名不能为空");
        }
        singerService.addSinger(singerVO);
        return new Result<>(true,HttpStatusEnum.OK, null);
    }
    @RequestMapping("updateSinger")
    public Result<Object> updateSinger(@RequestBody SingerVO singerVO) {
        //参数校验
        if (singerVO.getId()==null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, "歌手id不能为空");
        }
        singerService.updateSinger(singerVO);
        return new Result<>(true,HttpStatusEnum.OK, null);
    }
    @RequestMapping("deleteSinger")
    public Result<Object> deleteSinger(@RequestBody SingerVO singerVO) {
        //参数校验
        if (singerVO.getId()==null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, "歌手id不能为空");
        }
        singerService.deleteSinger(singerVO.getId());
        return new Result<>(true,HttpStatusEnum.OK, null);
    }

    //获取歌手类型
    @RequestMapping("getSingerType")
    @NotAuthorization
    public Result<Object> getSingerType() {
        return new Result<>(true,HttpStatusEnum.OK, singerService.getSingerType());
    }
    //获取歌手语言
    @RequestMapping("getSingerLanguage")
    @NotAuthorization
    public Result<Object> getSingerLanguage() {
        return new Result<>(true,HttpStatusEnum.OK, singerService.getSingerLanguage());
    }
    //条件获取歌手并分页
    @RequestMapping("getSingerConditionPage")
    @NotAuthorization
    public Result<Object> getSingerConditionPage(@RequestBody Page<SingerVO> page) {
        singerService.getSingerConditionPage(page);
        return new Result<>(true,HttpStatusEnum.OK,page);
    }
    @RequestMapping("getSingerById")
    @NotAuthorization
    public Result<Object> getSingerById( Integer id, Integer loginId) {
        return new Result<>(true,HttpStatusEnum.OK, singerService.getSingerById(id,loginId));
    }

}
