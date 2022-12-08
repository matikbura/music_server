package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.MyCollection;
import lombok.Data;

@Data
public class MyCollectionVO extends MyCollection {
    //创建时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] createTimeRange;
    //更新时间范围
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] modifiedTimeRange;
    //排序字段
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sortField;
    //loginId
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer loginId;
}
