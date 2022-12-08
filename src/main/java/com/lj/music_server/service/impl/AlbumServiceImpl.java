package com.lj.music_server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lj.music_server.entity.Album;
import com.lj.music_server.entity.Singer;
import com.lj.music_server.mapper.AlbumMapper;
import com.lj.music_server.mapper.SingerMapper;
import com.lj.music_server.service.IAlbumService;
import com.lj.music_server.utils.ParseUtils;
import com.lj.music_server.vo.AlbumVO;
import com.lj.music_server.vo.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class AlbumServiceImpl implements IAlbumService {
    final AlbumMapper albumMapper;
    final SingerMapper singerMapper;

    public AlbumServiceImpl(AlbumMapper albumMapper, SingerMapper singerMapper) {
        this.singerMapper= singerMapper;
        this.albumMapper = albumMapper;
    }

    @Override
    public ArrayList<AlbumVO> getAlbum(AlbumVO album) {
        ArrayList<Album> album1 = albumMapper.getAlbum(album);
        return ParseUtils.po_parse_vo(album1, AlbumVO.class);
    }

    @Override
    public void addAlbum(AlbumVO albumVO) {
        albumMapper.addAlbum(albumVO);
    }

    @Override
    public void getAlbumDetailPage(Page<AlbumVO> page) {
        //分页开始
        PageHelper.startPage(page.getPage(), page.getPageSize());
        ArrayList<Album> albums = albumMapper.getAlbumDetail(page.getCondition());
        PageInfo<Album> pageInfo = new PageInfo<>(albums);
        //po转vo
        ArrayList<AlbumVO> albumVOS = ParseUtils.po_parse_vo(pageInfo.getList(), AlbumVO.class);
        for (AlbumVO albumVO : albumVOS) {
            albumVO.setSinger(singerMapper.getSingerById(albumVO.getSingerId()));
        }
        //设置总数
        page.setTotal(pageInfo.getTotal());
        //设置数据
        page.setData(albumVOS);
    }

    @Override
    public void updateAlbum(AlbumVO albumVO) {
        albumMapper.updateAlbum(albumVO);
    }

    @Override
    public void deleteAlbum(AlbumVO albumVO) {
        albumMapper.deleteAlbum(albumVO);
    }
}
