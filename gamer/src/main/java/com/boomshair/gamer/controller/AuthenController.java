package com.boomshair.gamer.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.boomshair.gamer.domain.res.ResultBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author: liml
 * @date: 2022/7/31
 */
@RestController
public class AuthenController {

    @Value("${default_username}")
    private String defaultUsername;

    @Value("${default_password}")
    private String defaultPassword;

    @PostMapping("/login")
    public ResultBody login(@RequestParam String username, @RequestParam String password) {

        if (StpUtil.isDisable(username)) {
            return ResultBody.error("用户账号被锁定，还有" + StpUtil.getDisableTime(username) + "s解锁");
        }
        if ((Objects.equals(username, defaultUsername) && Objects.equals(password, defaultPassword))) {
            StpUtil.login(username);
            return ResultBody.successWithData(StpUtil.getTokenInfo());
        }
        return ResultBody.error("用户名或密码错误");
    }

    @PostMapping("/isLogin")
    public ResultBody isLogin() {
        if (StpUtil.isLogin()) {
            return ResultBody.successWithData(StpUtil.getTokenInfo());
        }
        return ResultBody.error("未登录");
    }



}
