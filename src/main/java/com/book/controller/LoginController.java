package com.book.controller;

import com.book.result.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import com.book.pojo.User;
import com.book.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping("/book")
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResult<Object> login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        String msg;
        Integer status;
        String code;
         if (null == user) {
            msg = "账号密码错误";
            status = 1;
            code = "300";
            return new JsonResult<>(code, msg, status);
        } else {
            msg = "success";
            status = 0;
            code = "200";
            return new JsonResult<>(code, msg, status);
        }
    }
}

