package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.service.RegisterService;
import com.ctgu.j604.springbootchat.service.TUserService;
import com.ctgu.j604.springbootchat.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
public class RegisterController {
    @Resource
    private TUserService tUserService;
    @Resource
    private RegisterService registerService;
    @Resource
    private LoginController loginController;
    @PostMapping("/register")
    public Result registerUser(TUser registertuser, HttpServletRequest req) {

        if(null==registertuser){
            return null;
        }
         TUser tUser = registerService.addTUser(registertuser);
       if (null!=tUser){
                req.getSession().setAttribute("curUserId",tUser.getUserId());
                req.getSession().setAttribute("userNum",tUser.getUserNum());
                req.getSession().setAttribute("password",tUser.getPassword());
//                req.getSession().setAttribute("userNum",tUser.getNickName());
//                req.getSession().setAttribute("userNum",tUser.getEmail());
                req.getSession().setAttribute("curUser",tUser);
//                req.getSession().setAttribute("curUser",tUser);
//                mv.setViewName("login");
                return new Result(true, "");
            } else {
                return new Result(false, Result.REPEAT_INFORMATION );

            }

        }
    }




