package com.lj.music_server.interceptor;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.lj.music_server.annotation.NotAuthorization;
import com.lj.music_server.exeption.NoneAuthException;
import com.lj.music_server.service.IUserService;
import com.lj.music_server.vo.UserVO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public class TokenInterceptor implements HandlerInterceptor {

    private String tokenKey;

    public TokenInterceptor(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token
        System.out.println("----------------------------------------------");
        for (Entry<String, String[]> entry: request.getParameterMap().entrySet()) {
            System.out.println(entry.getKey() + " : " + Arrays.toString(entry.getValue()));
        }
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有NotAuthorization注释，有则跳过认证
        if (method.isAnnotationPresent(NotAuthorization.class)) {
            NotAuthorization notAuthorization = method.getAnnotation(NotAuthorization.class);
            return true;
        }
        // 执行认证
        if (token == null) {
            throw new NoneAuthException("无token，请重新登录");
        }
        // 获取token中的user id
        String username=null;
        String password=null;
        try {
            JWT jwt = JWTUtil.parseToken(token);
            username = (String)jwt .getPayload("username");
            password = (String)jwt .getPayload("password");

        } catch (Exception e) {
            throw new NoneAuthException("401");
        }
        IUserService userService = SpringUtil.getBean(IUserService.class);
        UserVO userVO1 = new UserVO();
        userVO1.setUsername(username);
        userVO1.setPassword(password);
        UserVO userVO = userService.login(userVO1);
        if (userVO == null) {
            throw new NoneAuthException("用户不存在或密码已修改，请重新登录");
        }
//        JWTValidator jwtValidator = JWTValidator.of(token).validateDate(DateUtil.date());
        // 验证token
        boolean validate = JWT.of(token).setKey(tokenKey.getBytes()).validate(0);
        if (!JWT.of(token).setKey(tokenKey.getBytes()).validate(0)) {
            throw new NoneAuthException("token已过期，请重新登录");
        }
        //如果失效时间小于5分钟，生效时间加30分钟
        if ((Integer)JWT.of(token).setKey(tokenKey.getBytes()).getPayload(JWTPayload.EXPIRES_AT) - DateUtil.currentSeconds() < 300) {
            JWT jwt = JWT.of(token).setKey(tokenKey.getBytes());
            jwt.setPayload("exp", DateUtil.currentSeconds() + 1800);
            String newToken = jwt.sign();
            response.setHeader("token", newToken);
        }

        return true;
    }
}
