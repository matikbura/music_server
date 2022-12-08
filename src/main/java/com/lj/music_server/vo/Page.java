package com.lj.music_server.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Page <T>{
    private Integer page;
    private Integer pageSize;
    private Long total;
    private ArrayList<T> data;
    private T condition;
    private Boolean hasNext;
}
