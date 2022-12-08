package com.lj.music_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lj.music_server.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends User {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
}
