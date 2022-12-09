package com.lj.music_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lj.music_server.entity.MyCollection;
import com.lj.music_server.entity.Playlist;
import com.lj.music_server.entity.PlaylistStyle;
import com.lj.music_server.entity.PlaylistStyleTag;
import com.lj.music_server.mapper.MyCollectionMapper;
import com.lj.music_server.mapper.PlaylistMapper;
import com.lj.music_server.service.IPlaylistService;
import com.lj.music_server.utils.ParseUtils;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.PlaylistStyleVo;
import com.lj.music_server.vo.PlaylistVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class PlaylistServiceImpl implements IPlaylistService {
    final
    PlaylistMapper playlistMapper;
    final MyCollectionMapper myCollectionMapper;

    public PlaylistServiceImpl(PlaylistMapper playlistMapper, MyCollectionMapper myCollectionMapper) {
        this.playlistMapper = playlistMapper;
        this.myCollectionMapper = myCollectionMapper;
    }

    @Override
    public ArrayList<PlaylistVO> top(Integer limit) {
        ArrayList<Playlist> top = playlistMapper.top(limit);
        ArrayList<PlaylistVO> playlistVOS = new ArrayList<>();
        for (Playlist playlist : top) {
            playlistVOS.add(ParseUtils.po_parse_vo(playlist, PlaylistVO.class));
        }
        return playlistVOS;
    }

    @Override
    public ArrayList<PlaylistVO> getPlaylist(PlaylistVO playlist) {
        ArrayList<Playlist> playlists = playlistMapper.getPlaylist(playlist);
        ArrayList<PlaylistVO> playlistVOS1 = new ArrayList<>();
        for (Playlist playlist1 : playlists) {
            playlistVOS1.add(ParseUtils.po_parse_vo(playlist1, PlaylistVO.class));
        }
        return playlistVOS1;
    }

    @Override
    public int addPlaylist(PlaylistVO playlist) {
        return playlistMapper.addPlaylist(playlist);
    }

    @Override
    public void updatePlaylist(PlaylistVO playlist) {
        playlistMapper.updatePlaylist(playlist);
    }

    @Override
    public PlaylistVO getPlaylistById(Integer id, Integer userId) {
        Playlist playlist = playlistMapper.getPlaylistById(id);
        PlaylistVO playlistVO = ParseUtils.po_parse_vo(playlist, PlaylistVO.class);
        //获取歌单标签
        ArrayList<PlaylistStyleTag> playlistStyleTags = playlistMapper.getPlaylistStyleTagByPlaylistId(id);
        playlistVO.setTags(playlistStyleTags);
        if (userId != null) {
            MyCollection collection= new MyCollection();
            collection.setType(2);
            collection.setUserId(userId);
            collection.setTypeId(id);
            playlistVO.setIsCollected(myCollectionMapper.getMyCollection(collection).size() > 0);
        }
        return playlistVO;
    }

    @Override
    public ArrayList<PlaylistStyleVo> getPlaylistStyle() {
        ArrayList<PlaylistStyle> playlistStyle = playlistMapper.getPlaylistStyle();
        ArrayList<PlaylistStyleVo> playlistStyleVos = ParseUtils.po_parse_vo(playlistStyle, PlaylistStyleVo.class);
        for (PlaylistStyleVo playlistStyleVo : playlistStyleVos) {
            playlistStyleVo.setTags(playlistMapper.getPlaylistStyleTagByPlaylistStyleId(playlistStyleVo.getId()));
        }
        return playlistStyleVos;
    }

    @Override
    public void addPlaylistStyle(@RequestBody PlaylistStyle style) {
        playlistMapper.addPlaylistStyle(style);
    }

    @Override
    public void addPlaylistStyleTag(PlaylistStyleTag style) {
        playlistMapper.addPlaylistStyleTag(style);
    }

    @Override
    public void getPlaylistConditionPage(Page<PlaylistVO> page) {
        PageHelper.startPage(page.getPage(), page.getPageSize());
        ArrayList<Playlist> playlist = playlistMapper.getPlaylist(page.getCondition());
        PageInfo<Playlist> pageInfo = new PageInfo<>(playlist);
        ArrayList<PlaylistVO> playlistVOS = ParseUtils.po_parse_vo(pageInfo.getList(), PlaylistVO.class);
        MyCollection collection = null;
        if(page.getCondition().getLoginId()!=null){
            for (PlaylistVO playlistVO : playlistVOS) {
                collection= new MyCollection();
                collection.setType(2);
                collection.setUserId(page.getCondition().getLoginId());
                collection.setTypeId(playlistVO.getId());
                playlistVO.setIsCollected(myCollectionMapper.getMyCollection(collection).size() > 0);
            }
        }
        page.setTotal(pageInfo.getTotal());
        page.setData(playlistVOS);
        page.setHasNext(pageInfo.isHasNextPage());

    }


}
