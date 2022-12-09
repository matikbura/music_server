package com.lj.music_server.mapper;

import com.lj.music_server.entity.User;
import com.lj.music_server.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User login(User user);

    User getUserById(Integer userId);
}
