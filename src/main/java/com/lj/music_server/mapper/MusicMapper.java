package com.lj.music_server.mapper;

import com.lj.music_server.entity.Lyric;
import com.lj.music_server.entity.Music;
import com.lj.music_server.vo.MusicVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MusicMapper {
    ArrayList<Music> getMusic(MusicVO id);

    void addMusic(MusicVO music);

    ArrayList<Music> top(Integer limit);

    Music getMusicById(Integer id);

    ArrayList<Music> getPlaylistMusic(MusicVO playlist);

    void addMusicToPlaylist(MusicVO musicVO);

    void addMusicLyric(MusicVO musicVO);

    void addMusicSinger(MusicVO musicVO);

    void updateMusic(MusicVO musicVO);

    Lyric getLyricByMusicId(Integer id);

}
