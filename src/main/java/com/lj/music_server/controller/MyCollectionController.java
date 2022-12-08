package com.lj.music_server.controller;

import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.service.IMyCollectionService;
import com.lj.music_server.vo.MyCollectionVO;
import com.lj.music_server.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("myCollection")
public class MyCollectionController {
    final IMyCollectionService myCollectionService;

    public MyCollectionController(IMyCollectionService myCollectionService) {
        this.myCollectionService = myCollectionService;
    }

    @RequestMapping("addCollection")
    public Result<Object> addCollection(MyCollectionVO myCollectionVO) {
        myCollectionService.addCollection(myCollectionVO);
        return new Result<>(true, HttpStatusEnum.OK, null);
    }
    @RequestMapping("deleteCollection")
    public Result<Object>  deleteCollection(MyCollectionVO myCollectionVO) {
        myCollectionService.deleteCollection(myCollectionVO);
        return new Result<>(true, HttpStatusEnum.OK, null);
    }
}
