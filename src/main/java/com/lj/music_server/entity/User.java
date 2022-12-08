package com.lj.music_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data

public class User {
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @JsonIgnore
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sex;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String birthday;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modifiedTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String avatar;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nickname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer role;
}
