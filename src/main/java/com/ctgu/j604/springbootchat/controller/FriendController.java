package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.service.FriendService;
import com.ctgu.j604.springbootchat.utils.Result;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FriendController {

    @Resource
    private FriendService friendService;

    @RequestMapping("/friend/detail")
    public Object getFriendsDetail(){
        return null;
    }

    @RequestMapping("/friend/delete")
    public Result deleteFriend(){
        return null;
    }

    @RequestMapping("/friend/modify")
    public @ResponseBody Result modifyFriend(HttpServletRequest request, String newComment, String toModifyFriendNum){
        TUser tUser = (TUser) request.getSession().getAttribute("curUser");
//        if(newComment==null && "".equals(newComment)){
//            newComment = ""
//        }
        boolean res = false;
        if(tUser != null){
             res = friendService.modifyFriendComment(tUser,toModifyFriendNum,newComment);
        }
        return new Result(res,"");
    }

}
