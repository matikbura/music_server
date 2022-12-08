package com.lj.music_server.controller;

import com.lj.music_server.annotation.NotAuthorization;
import com.lj.music_server.entity.Playlist;
import com.lj.music_server.entity.PlaylistStyle;
import com.lj.music_server.entity.PlaylistStyleTag;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.PlaylistVO;
import com.lj.music_server.vo.Result;
import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.service.IPlaylistService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("playlist")
public class PlaylistController {
    final
    IPlaylistService playlistService;

    public PlaylistController(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @NotAuthorization
    @RequestMapping("top")
    public Result<Object> top(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        ArrayList<PlaylistVO> musicList = playlistService.top(limit);
        return new Result<>(true, HttpStatusEnum.OK, musicList);
    }
    @NotAuthorization
    @RequestMapping("getPlaylist")
    public Result<Object> getPlaylist(PlaylistVO playlist) {
        ArrayList<PlaylistVO> musicList = playlistService.getPlaylist(playlist);
        return new Result<>(true, HttpStatusEnum.OK, musicList);
    }
    @NotAuthorization
    @RequestMapping("getPlaylistById")
    public Result<Object> getPlaylistById(Integer id) {
        PlaylistVO playlist = playlistService.getPlaylistById(id);
        return new Result<>(true, HttpStatusEnum.OK, playlist);
    }

    @RequestMapping("addPlaylist")
    public Result<Object> addPlaylist(PlaylistVO playlist) {
        if (playlist.getName() == null||playlist.getUserId()==null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        playlistService.addPlaylist(playlist);
        return new Result<>(true, HttpStatusEnum.OK,playlist );
    }
    @RequestMapping("updatePlaylist")
    public Result<Object> updatePlaylist(PlaylistVO playlist) {
        if (playlist.getId() == null||playlist.getUserId()==null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        playlistService.updatePlaylist(playlist);
        return new Result<>(true, HttpStatusEnum.OK, null);
    }

    @RequestMapping("getPlaylistStyle")
    public Result<Object> getPlaylistStyle() {
        return new Result<>(true, HttpStatusEnum.OK, playlistService.getPlaylistStyle());
    }
    @RequestMapping("addPlaylistStyle")
    public Result<Object> addPlaylistStyle(PlaylistStyle style) {
        if (style == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        playlistService.addPlaylistStyle(style);
        return new Result<>(true, HttpStatusEnum.OK, style);
    }
    @RequestMapping("addPlaylistStyleTag")
    public Result<Object> addPlaylistStyleTag(PlaylistStyleTag style) {
        if (style == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, null);
        }
        playlistService.addPlaylistStyleTag(style);
        return new Result<>(true, HttpStatusEnum.OK, style);
    }
    @RequestMapping("getPlaylistConditionPage")
    public Result<Object> getPlaylistConditionPage(@RequestBody Page<PlaylistVO> page) {
        playlistService.getPlaylistConditionPage(page);
        return new Result<>(true, HttpStatusEnum.OK, page);
    }

}
