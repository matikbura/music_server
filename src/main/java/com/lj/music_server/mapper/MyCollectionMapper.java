package com.lj.music_server.mapper;

import com.lj.music_server.entity.MyCollection;
import com.lj.music_server.vo.MyCollectionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyCollectionMapper {
    List<MyCollection> getMyCollection(MyCollection myCollection);

    void addCollection(MyCollectionVO myCollectionVO);

    void deleteCollection(MyCollectionVO myCollectionVO);

}
