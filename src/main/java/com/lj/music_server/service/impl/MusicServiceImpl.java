package com.lj.music_server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lj.music_server.entity.Album;
import com.lj.music_server.entity.Lyric;
import com.lj.music_server.entity.Music;
import com.lj.music_server.entity.Singer;
import com.lj.music_server.mapper.AlbumMapper;
import com.lj.music_server.mapper.MusicMapper;
import com.lj.music_server.mapper.SingerMapper;
import com.lj.music_server.service.IMusicService;
import com.lj.music_server.utils.ParseUtils;
import com.lj.music_server.vo.MusicVO;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.PlaylistVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MusicServiceImpl implements IMusicService {
    final
    MusicMapper musicMapper;
    final AlbumMapper albumMapper;
    final SingerMapper singerMapper;

    public MusicServiceImpl(MusicMapper musicMapper, AlbumMapper albumMapper, SingerMapper singerMapper) {
        this.musicMapper = musicMapper;
        this.albumMapper = albumMapper;
        this.singerMapper = singerMapper;
    }

    @Override
    public ArrayList<MusicVO> getMusic(MusicVO musicVO) {
        //Bean转Map   Map转Bean实现POJO与VO的转换
        ArrayList<Music> musicList = musicMapper.getMusic(musicVO);
        ArrayList<MusicVO> musicVOS = ParseUtils.po_parse_vo(musicList, MusicVO.class);
        for (MusicVO musicVOItem : musicVOS) {
            Album albumById = albumMapper.getAlbumById(musicVOItem.getAlbumId());
            musicVOItem.setAlbum(albumById);
            ArrayList<Singer> singerByMusicId = singerMapper.getSingerByMusicId(musicVOItem.getId());
            musicVOItem.setSingers(singerByMusicId);
        }
        return musicVOS;
    }
    @Override
    public ArrayList<MusicVO> getPlaylistMusic(MusicVO playlist) {
        ArrayList<Music> musicList = musicMapper.getPlaylistMusic(playlist);
        ArrayList<MusicVO> musicVOS = ParseUtils.po_parse_vo(musicList, MusicVO.class);
        for (MusicVO musicVOItem : musicVOS) {
            Album albumById = albumMapper.getAlbumById(musicVOItem.getAlbumId());
            musicVOItem.setAlbum(albumById);
            ArrayList<Singer> singerByMusicId = singerMapper.getSingerByMusicId(musicVOItem.getId());
            musicVOItem.setSingers(singerByMusicId);
        }
        return musicVOS;
    }

    @Override
    public void addMusic(MusicVO musicvo) {
        musicMapper.addMusic(musicvo);
        //添加歌词
        musicMapper.addMusicLyric(musicvo);
        //添加歌手与歌曲关联
        musicMapper.addMusicSinger(musicvo);
    }

    @Override
    public ArrayList<MusicVO> top(Integer limit) {
        ArrayList<Music> music = musicMapper.top(limit);
        ArrayList<MusicVO> musicVOS = ParseUtils.po_parse_vo(music, MusicVO.class);
        Album albumById = null;
        for (MusicVO musicVO : musicVOS) {
            albumById = albumMapper.getAlbumById(musicVO.getAlbumId());
            musicVO.setAlbum(albumById);
        }
        return musicVOS;
    }

    @Override
    public MusicVO getMusicById(Integer id) {
        return ParseUtils.po_parse_vo(musicMapper.getMusicById(id), MusicVO.class);
    }

    @Override
    public void addMusicToPlaylist(MusicVO musicVO) {
        //调用musicMapper的addMusicToPlaylist方法
        musicMapper.addMusicToPlaylist(musicVO);
    }

    @Override
    public Lyric getLyricByMusicId(Integer id) {
        return musicMapper.getLyricByMusicId(id);
    }

    @Override
    public void getMusicDetailPage(Page<MusicVO> page) {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        ArrayList<Music> musicList = musicMapper.getMusic(page.getCondition());
        PageInfo<Music> pageInfo = new PageInfo<>(musicList);
        page.setTotal(pageInfo.getTotal());
        //po转vo
        ArrayList<MusicVO> musicVOS1 = ParseUtils.po_parse_vo(pageInfo.getList(), MusicVO.class);
        //遍历歌曲
        for (MusicVO musicVOItem : musicVOS1) {
            //根据歌曲id获取专辑
            Album albumById = albumMapper.getAlbumById(musicVOItem.getAlbumId());
            musicVOItem.setAlbum(albumById);
            //根据歌曲id获取歌手
            ArrayList<Singer> singerByMusicId = singerMapper.getSingerByMusicId(musicVOItem.getId());
            musicVOItem.setSingers(singerByMusicId);
            //歌迷歌曲id获取歌词
            Lyric lyricByMusicId = musicMapper.getLyricByMusicId(musicVOItem.getId());
            musicVOItem.setLyric(lyricByMusicId);
        }

        String test= "111+222.3";
        //结尾是否为小数
        boolean b = test.matches(".*[.]\\d+$");
        page.setData(musicVOS1);
    }

    @Override
    public void getMusicConditionPage(Page<MusicVO> page) {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        ArrayList<Music> musicList = musicMapper.getMusic(page.getCondition());
        PageInfo<Music> pageInfo = new PageInfo<>(musicList);
        page.setTotal(pageInfo.getTotal());
        //po转vo
        ArrayList<MusicVO> musicVOS1 = ParseUtils.po_parse_vo(pageInfo.getList(), MusicVO.class);
        //遍历歌曲
        for (MusicVO musicVOItem : musicVOS1) {
            //根据歌曲id获取专辑
            Album albumById = albumMapper.getAlbumById(musicVOItem.getAlbumId());
            musicVOItem.setAlbum(albumById);
            //根据歌曲id获取歌手
            ArrayList<Singer> singerByMusicId = singerMapper.getSingerByMusicId(musicVOItem.getId());
            musicVOItem.setSingers(singerByMusicId);
        }
        page.setData(musicVOS1);
    }
}
