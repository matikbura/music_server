package com.lj.music_server.service.impl;

import com.lj.music_server.mapper.UserMapper;
import com.lj.music_server.service.IUserService;
import com.lj.music_server.utils.ParseUtils;
import com.lj.music_server.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserVO login(UserVO userVO) {
        UserVO userVO1 = ParseUtils.po_parse_vo(userMapper.login(userVO), UserVO.class);
        if (userVO1 == null) {
            return null;
        }
        return userVO1;
    }
}
