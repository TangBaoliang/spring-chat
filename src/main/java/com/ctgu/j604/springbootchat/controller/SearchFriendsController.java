package com.ctgu.j604.springbootchat.controller;


import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.service.TUserService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchFriendsController {

    @Resource
    TUserService tUserService;

    @RequestMapping("/search")
    public Object searchFriends(TUser tuser){

        //用于存储找到的人
        List<TUser> friendsList = new ArrayList<>();

        //处理年龄
        Integer ageBegin = 0;
        Integer ageEnd = 0;
        if(tuser.getAge().equals(1)){
            ageBegin = 16;
            ageEnd = 20;
        }else if(tuser.getAge().equals(2)){
            ageBegin = 21;
            ageEnd = 25;
        }

        //判断是否是号码
        if(tuser.getUserNum().matches("[0-9]+")){
            friendsList.addAll(tUserService.getTUserByUserNumAndAgeAndSex(tuser,ageBegin,ageEnd));
            tuser.setNickName(tuser.getUserNum());
            tuser.setUserNum(null);
            friendsList.addAll(tUserService.getTUserByNickNameAndAgeAndSex(tuser,ageBegin,ageEnd));
        }else{
            tuser.setNickName(tuser.getUserNum());
            tuser.setUserNum(null);
            friendsList.addAll(tUserService.getTUserByNickNameAndAgeAndSex(tuser, ageBegin, ageEnd));
        }
        return friendsList;
    }

}
