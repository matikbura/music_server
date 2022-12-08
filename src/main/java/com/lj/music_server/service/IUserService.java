package com.lj.music_server.service;

import com.lj.music_server.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

public interface IUserService {
    UserVO login(UserVO userVO);
}
