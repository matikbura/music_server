package com.lj.music_server.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.music_server.entity.Album;
import com.lj.music_server.entity.Music;
import com.lj.music_server.entity.Playlist;
import com.lj.music_server.vo.MusicVO;
import com.lj.music_server.vo.SingerVO;
import com.lj.music_server.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {


    public static <T, E> T po_parse_vo(E data, Class<T> target) {
        return BeanUtil.toBean(BeanUtil.beanToMap(data), target);
    }

    public static <T, E> ArrayList<T> po_parse_vo(List<E> data, Class<T> target) {
        ArrayList<T> result = new ArrayList<>();
        for (E e : data) {
            result.add(po_parse_vo(e, target));
        }
        return result;
    }

}
