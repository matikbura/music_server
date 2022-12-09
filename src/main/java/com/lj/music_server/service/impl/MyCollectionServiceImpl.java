package com.lj.music_server.service.impl;

import com.lj.music_server.entity.Album;
import com.lj.music_server.entity.Playlist;
import com.lj.music_server.entity.Singer;
import com.lj.music_server.enums.TypeEnum;
import com.lj.music_server.mapper.*;
import com.lj.music_server.service.*;
import com.lj.music_server.utils.ParseUtils;
import com.lj.music_server.vo.AlbumVO;
import com.lj.music_server.vo.MyCollectionVO;
import com.lj.music_server.vo.PlaylistVO;
import com.lj.music_server.vo.SingerVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class MyCollectionServiceImpl implements IMyCollectionService {
    final MyCollectionMapper myCollectionMapper;
    final MusicMapper musicMapper;
    final PlaylistMapper playlistMapper;
    final AlbumMapper albumMapper;
    final SingerMapper singerMapper;

    public MyCollectionServiceImpl(MyCollectionMapper myCollectionMapper, MusicMapper musicMapper, PlaylistMapper playlistMapper, AlbumMapper albumMapper, SingerMapper singerMapper) {
        this.myCollectionMapper = myCollectionMapper;
        this.musicMapper = musicMapper;
        this.playlistMapper = playlistMapper;
        this.albumMapper = albumMapper;
        this.singerMapper = singerMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCollection(MyCollectionVO myCollectionVO) {
        myCollectionMapper.addCollection(myCollectionVO);

        if (myCollectionVO.getType()==TypeEnum.ALBUM.getTypeNum()){
            Album album = albumMapper.getAlbumById(myCollectionVO.getTypeId());
            album.setCollectionCount(album.getCollectionCount() + 1);
            AlbumVO albumVO = ParseUtils.po_parse_vo(album, AlbumVO.class);
            albumMapper.updateAlbum(albumVO);
        }else if (myCollectionVO.getType()==TypeEnum.PLAYLIST.getTypeNum()){
            Playlist playlist = playlistMapper.getPlaylistById(myCollectionVO.getTypeId());
            playlist.setCollectionCount(playlist.getCollectionCount() + 1);
            playlistMapper.updatePlaylist(playlist);
        }else if (myCollectionVO.getType()==TypeEnum.SINGER.getTypeNum()){
            Singer singer = singerMapper.getSingerById(myCollectionVO.getTypeId());
            singer.setCollectionCount(singer.getCollectionCount() + 1);
            SingerVO singerVO = ParseUtils.po_parse_vo(singer, SingerVO.class);
            singerMapper.updateSinger(singerVO);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteCollection(MyCollectionVO myCollectionVO) {
        myCollectionMapper.deleteCollection(myCollectionVO);
        TypeEnum album = TypeEnum.ALBUM;
        if(myCollectionVO.getType()==TypeEnum.ALBUM.getTypeNum()){
            Album albumById = albumMapper.getAlbumById(myCollectionVO.getTypeId());
            albumById.setCollectionCount(albumById.getCollectionCount()-1);
            AlbumVO albumVO = ParseUtils.po_parse_vo(albumById, AlbumVO.class);
            albumMapper.updateAlbum(albumVO);
        }else if(myCollectionVO.getType()== TypeEnum.PLAYLIST.getTypeNum()){
            Playlist playlistById = playlistMapper.getPlaylistById(myCollectionVO.getTypeId());
            playlistById.setCollectionCount(playlistById.getCollectionCount()-1);
            playlistMapper.updatePlaylist(playlistById);
        } else if (myCollectionVO.getType()== TypeEnum.SINGER.getTypeNum()) {
            Singer singerById = singerMapper.getSingerById(myCollectionVO.getTypeId());
            singerById.setCollectionCount(singerById.getCollectionCount()-1);
            SingerVO singerVO = ParseUtils.po_parse_vo(singerById, SingerVO.class);
            singerMapper.updateSinger(singerVO);
        }
    }
}
