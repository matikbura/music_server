package com.lj.music_server.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lj.music_server.annotation.NotAuthorization;
import com.lj.music_server.entity.Lyric;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.PlaylistVO;
import com.lj.music_server.vo.Result;

import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.service.IMusicService;
import com.lj.music_server.vo.MusicVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("music")
public class MusicController {
    final
    IMusicService musicService;

    public MusicController(IMusicService musicService) {
        this.musicService = musicService;
    }
    //通过ID获取歌曲
    @NotAuthorization
    @RequestMapping("getMusicById")
    public Result<Object> getMusicById(Integer id) {
        if (id == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        MusicVO music = musicService.getMusicById(id);
        return new Result<>(true, HttpStatusEnum.OK, music);
    }
    //获取歌曲
    @NotAuthorization
    @RequestMapping("getMusic")
    public Result<Object> getMusic( MusicVO musicVO) {
        ArrayList<MusicVO> music = musicService.getMusic(musicVO);
        return new Result<>(true, HttpStatusEnum.OK, music);
    }
    //获取前五点击量的歌曲
    @NotAuthorization
    @RequestMapping("top")
    public Result<Object> top(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        return new Result<>(true, HttpStatusEnum.OK, musicService.top(limit));
    }
    //查找歌单中的音乐
    @NotAuthorization
    @RequestMapping("getPlaylistMusic")
    public Result<Object> getPlaylistMusic(MusicVO musicVO) {

        if (musicVO.getPlaylistId() == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        ArrayList<MusicVO> musicList = musicService.getPlaylistMusic(musicVO);
        return new Result<>(true, HttpStatusEnum.OK, musicList);
    }
    @NotAuthorization
    @RequestMapping("getLyricByMusicId")
    public Result<Object> getLyricByMusicId(Integer id) {
        if (id == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        Lyric lyric = musicService.getLyricByMusicId(id);
        return new Result<>(true, HttpStatusEnum.OK,lyric );
    }



    //添加歌曲
    @RequestMapping("addMusic")
    public Result<Object> addMusic(@RequestBody HashMap<String,Object> tempMap) {
        //参数校验
        MusicVO music = BeanUtil.toBean(tempMap, MusicVO.class);
        if (music.getAlbumId() == null || music.getName() == null || music.getUrl() == null || music.getDuration() == null || music.getDurationSecond() == null || music.getSingerIdList() == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        musicService.addMusic(music);
        return new Result<>(true, HttpStatusEnum.OK, null);
    }


    //添加音乐到歌单
    @RequestMapping("addMusicToPlaylist")
    public Result<Object> addMusicToPlaylist(MusicVO musicVO) {
        //参数校验
        if (musicVO.getPlaylistId() == null || musicVO.getId() == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        ArrayList<MusicVO> playlistMusic = musicService.getPlaylistMusic(musicVO);
        if (playlistMusic.size() > 0) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, "歌单中已存在该歌曲");
        }
        //调用musicService服务的addMusicToPlaylist方法实现添加音乐到歌单的操作
        musicService.addMusicToPlaylist(musicVO);
        //返回成功的信息给前端
        return new Result<>(true, HttpStatusEnum.OK, null);
    }
    @RequestMapping("getMusicDetailPage")
    public Result<Object> getMusicDetailPage(@RequestBody Page<MusicVO> page) {
         musicService.getMusicDetailPage(page);
        return new Result<>(true, HttpStatusEnum.OK, page);
    }
    @RequestMapping("getMusicConditionPage")
    @NotAuthorization
    public Result<Object> getMusicConditionPage(@RequestBody Page<MusicVO> page) {
        musicService.getMusicConditionPage(page);
        return new Result<>(true, HttpStatusEnum.OK, page);
    }
}
