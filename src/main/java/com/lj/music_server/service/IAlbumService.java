package com.lj.music_server.service;

import com.lj.music_server.vo.AlbumVO;
import com.lj.music_server.vo.Page;

import java.util.ArrayList;

public interface IAlbumService {
    ArrayList<AlbumVO> getAlbum(AlbumVO album);

    void addAlbum(AlbumVO albumVO);

    void getAlbumDetailPage(Page<AlbumVO> page);

    void updateAlbum(AlbumVO albumVO);

    void deleteAlbum(AlbumVO albumVO);

}
