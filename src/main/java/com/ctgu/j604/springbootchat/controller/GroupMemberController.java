package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.service.GroupMemberService;
import com.ctgu.j604.springbootchat.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GroupMemberController {
    @Resource
    private GroupMemberService groupMemberService;

    @ResponseBody
    @RequestMapping("/groupMember/modifyComment")
    public Object modifyCmtInGroup(HttpServletRequest request,String groupNum, String newGroupComment){
        boolean res = false;
        TUser tUser = (TUser)request.getSession().getAttribute("curUser");
        if(tUser!=null){
            res = groupMemberService.modifyGroupMemberComment(tUser,groupNum,newGroupComment);
        }
        return new Result(res,"");
    }
}
