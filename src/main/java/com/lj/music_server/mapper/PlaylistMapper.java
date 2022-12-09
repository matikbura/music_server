package com.lj.music_server.mapper;

import com.lj.music_server.entity.Playlist;
import com.lj.music_server.entity.PlaylistStyle;
import com.lj.music_server.entity.PlaylistStyleTag;
import com.lj.music_server.vo.MusicVO;
import com.lj.music_server.vo.PlaylistVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PlaylistMapper {
    ArrayList<Playlist> top(Integer limit);

    ArrayList<Playlist> getPlaylist(Playlist playlist);

    int addPlaylist(Playlist playlist);

    void updatePlaylist(Playlist playlist);

    Playlist getPlaylistById(Integer id);

    ArrayList<PlaylistStyle> getPlaylistStyle();

    ArrayList<PlaylistStyleTag> getPlaylistStyleTagByPlaylistStyleId(Integer id);

    void addPlaylistStyle(PlaylistStyle style);

    void addPlaylistStyleTag(PlaylistStyleTag style);

    ArrayList<PlaylistStyleTag> getPlaylistStyleTagByPlaylistId(Integer id);

    void addClickCount(Integer typeId);
}
