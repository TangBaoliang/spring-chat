package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.utils.Result;
import com.ctgu.j604.springbootchat.service.TUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Resource
    private TUserService tUserService;

    @PostMapping("/login")
    public Result login(HttpServletRequest request,TUser user){

        if(null==user){
            return null;
        }
        TUser tUser = tUserService.login(user);
        if(null!=tUser){
            request.getSession().setAttribute("curUserId",tUser.getUserId());
            request.getSession().setAttribute("userNum",tUser.getUserNum());
            request.getSession().setAttribute("curUser",tUser);
            return new Result(true,"");
        }
        else{
            return new Result(false, Result.WRONG_ACCOUNT_OR_PASSWORD);
        }
    }
}
