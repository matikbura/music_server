package com.lj.music_server.vo;

import com.lj.music_server.enums.HttpStatusEnum;
import lombok.Data;

@Data
public class Result<T> {
    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public Result(boolean success, HttpStatusEnum enums,T data) {
        this.success = success;
        this.code = enums.getCode();
        this.message = enums.getReasonPhraseCN();
        this.data=data;
    }
}
