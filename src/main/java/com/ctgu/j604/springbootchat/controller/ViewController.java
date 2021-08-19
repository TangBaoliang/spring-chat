package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.model.FriendListInfo;
import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.service.TUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ViewController {
    @Resource
    private TUserService tUserService;

    @RequestMapping("/main")
    public ModelAndView retMain(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("HeartChat");
        Integer curUserId = (Integer) request.getSession().getAttribute("curUserId");
        List<FriendListInfo> friendListInfoList = tUserService.getAllFriend(curUserId);
        TUser tUser = (TUser)request.getSession().getAttribute("curUser");
        mv.addObject("curUser",tUser);
        mv.addObject("friendListInfoList",friendListInfoList);
        return mv;
    }
    @RequestMapping("/")
    public ModelAndView loginHtml(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
