package com.lj.music_server.mapper;

import com.lj.music_server.entity.Album;
import com.lj.music_server.vo.AlbumVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Mapper
public interface AlbumMapper {
    Album getAlbumById(Integer albumId);

    ArrayList<Album> top(Integer limit);
    ArrayList<Album> getAlbum(AlbumVO album);
    void addAlbum(AlbumVO albumVO);
    void updateAlbum(AlbumVO albumVO);
    ArrayList<Album> getAlbumDetail(AlbumVO condition);

    void deleteAlbum(AlbumVO albumVO);
}
