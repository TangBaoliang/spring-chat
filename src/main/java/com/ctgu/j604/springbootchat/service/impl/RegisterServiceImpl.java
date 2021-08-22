package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.TUserMapper;
import com.ctgu.j604.springbootchat.model.FriendListInfoExample;
import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.model.TUserExample;
import com.ctgu.j604.springbootchat.service.RegisterService;
import com.ctgu.j604.springbootchat.utils.MessageUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Component
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public TUser addTUser(TUser registertuser) {
        String userNum = "";
        Random random = new Random();
        for(int i=0;i<10;i++ ){
            if(i==0){
                String temp=String.valueOf(random.nextInt(10));
                while(temp.equals("0")){
                    temp=String.valueOf(random.nextInt(10));
                }
                userNum+=temp;
            }
            else
            userNum+=String.valueOf(random.nextInt(10));
            }
        registertuser.setUserNum(userNum);
        //判断是否重
        TUserExample UserExample = new TUserExample();
        TUserExample.Criteria criteria1=UserExample.or();
        TUserExample.Criteria criteria2=UserExample.or();
        TUserExample.Criteria criteria3=UserExample.or();
        criteria1.andNickNameEqualTo(registertuser.getNickName());
        criteria2.andEmailEqualTo(registertuser.getEmail());
        criteria3.andUserNumEqualTo(registertuser.getUserNum());
        if(tUserMapper.selectByExample(UserExample).size()>0){
            System.out.println(tUserMapper.selectByExample(UserExample));
            return  null;
        }
        else
         return tUserMapper.insertSelective(registertuser)>0?registertuser:null;
    }
}
