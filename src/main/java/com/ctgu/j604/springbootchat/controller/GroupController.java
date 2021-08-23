package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.service.GroupService;
import com.ctgu.j604.springbootchat.service.MessageService;
import com.ctgu.j604.springbootchat.utils.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GroupController {
    @Resource
    private GroupService groupService;
    @Resource
    private MessageService messageService;

    @PostMapping("/group/create")
    @Transactional
    public Result createGroup(HttpServletRequest request,@RequestBody String[] toInviteUserNums,String groupName){
        TUser tUser = (TUser) request.getSession().getAttribute("curUser");
        if (groupName==null || groupName.equals("")){
            groupName = tUser.getNickName()+"创建的群聊";
        }
        String groupNum = groupService.createGroup(groupName,tUser);
        if(groupNum!=null && !groupNum.equals("")){
            if (toInviteUserNums!=null && toInviteUserNums.length>0){
                messageService.sendInviteMessage(tUser,toInviteUserNums,groupNum,groupName);
            }
            return new Result(true,groupNum);
        }
        else{
            return new Result(false,"");
        }
    }

}
