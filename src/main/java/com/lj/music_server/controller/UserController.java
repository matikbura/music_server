package com.lj.music_server.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import com.lj.music_server.annotation.NotAuthorization;
import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.service.IUserService;
import com.lj.music_server.service.impl.UserServiceImpl;
import com.lj.music_server.vo.Result;
import com.lj.music_server.vo.UserVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Value("${token.key}")
    String tokenKey;
    final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @NotAuthorization
    @RequestMapping("login")
    public Result<Object> login(UserVO userVO) {
        //如果用户名或者为密码为空，就返回一个错误的请求
        if (StrUtil.isEmpty(userVO.getUsername()) || StrUtil.isEmpty(userVO.getPassword())) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, "用户名或密码不能为空");
        }
        //调用service层的login方法
        UserVO login = userService.login(userVO);
        // 如果返回的login为空，就返回一个错误的请求
        if (login == null) {
            return new Result<>(false, HttpStatusEnum.BAD_REQUEST, "用户名或密码错误");
        }
        //如果不为空，使用Hutool工具包创建一个token号，并把查询到的信息一起返回给前端
        String token = JWT.create().setNotBefore(DateUtil.date())
                .setExpiresAt(DateUtil.offsetMinute(DateUtil.date(), 30))
                .setPayload("username", userVO.getUsername())
                .setPayload("password", userVO.getPassword())
                .setKey(tokenKey.getBytes())
                .sign();
        login.setToken(token);
        return new Result<>(true, HttpStatusEnum.OK, login);
    }
    @NotAuthorization
    @RequestMapping("getUserById")
    public Result<Object> getUserById(Integer userId) {
        UserVO userById = userService.getUserById(userId);
        return new Result<>(true, HttpStatusEnum.OK, userById);
    }

    @NotAuthorization
    @RequestMapping("register")
    public String register() {
        return "user/register";
    }
}
