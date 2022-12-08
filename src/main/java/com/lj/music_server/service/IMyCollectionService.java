package com.lj.music_server.service;

import com.lj.music_server.vo.MyCollectionVO;

public interface IMyCollectionService {
    void addCollection(MyCollectionVO myCollectionVO);

    void deleteCollection(MyCollectionVO myCollectionVO);
}
