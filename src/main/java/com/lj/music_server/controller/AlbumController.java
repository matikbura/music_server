package com.lj.music_server.controller;

import com.lj.music_server.annotation.NotAuthorization;
import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.service.IAlbumService;
import com.lj.music_server.service.impl.AlbumServiceImpl;
import com.lj.music_server.vo.AlbumVO;
import com.lj.music_server.vo.Page;
import com.lj.music_server.vo.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("album")
public class AlbumController {
    final IAlbumService albumService;

    public AlbumController(IAlbumService albumService) {
        this.albumService = albumService;
    }

    @NotAuthorization
    @RequestMapping("getAlbum")
    public Result<Object> getAlbum(@RequestBody AlbumVO album) {
        return new Result<>(true, HttpStatusEnum.OK, albumService.getAlbum(album));
    }

    @RequestMapping("addAlbum")
    public Result<Object> addAlbum(@RequestBody AlbumVO albumVO) {
        //参数校验
        if (albumVO.getName() == null || albumVO.getName().equals("")) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, "专辑名不能为空");
        }
        System.out.println(albumVO);
        albumService.addAlbum(albumVO);
        return new Result<>(true, HttpStatusEnum.OK, null);
    }
    @RequestMapping("getAlbumDetailPage")
    public Result<Object> getAlbumDetailPage(@RequestBody Page<AlbumVO> page) {
        albumService.getAlbumDetailPage(page);
        return new Result<>(true, HttpStatusEnum.OK, page);
    }
    @RequestMapping("updateAlbum")
    public Result<Object> updateAlbum(@RequestBody AlbumVO albumVO) {
        albumService.updateAlbum(albumVO);
        return new Result<>(true, HttpStatusEnum.OK, null);
    }
    @RequestMapping("deleteAlbum")
    public Result<Object> deleteAlbum(@RequestBody AlbumVO albumVO) {
        albumService.deleteAlbum(albumVO);
        return new Result<>(true, HttpStatusEnum.OK, null);
    }
}
