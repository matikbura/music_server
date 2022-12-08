package com.lj.music_server.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum TypeEnum {
    MUSIC(1, "专辑"),
    PLAYLIST(2, "歌单"),
    SINGER(3, "歌手"),
    ALBUM(4, "音乐"),
    MV(5, "MV"),
    ;
    final int typeNum;
    final String typeName;
    TypeEnum(int typeNum, String typeName) {
        this.typeNum = typeNum;
        this.typeName = typeName;
    }
}
