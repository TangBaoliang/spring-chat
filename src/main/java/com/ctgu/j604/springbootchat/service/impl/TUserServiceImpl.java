package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.*;
import com.ctgu.j604.springbootchat.model.*;
import com.ctgu.j604.springbootchat.service.GroupService;
import com.ctgu.j604.springbootchat.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private FriendListInfoMapper friendListInfoMapper;
    @Resource
    private UnreadMessageMapper unreadMessageMapper;
    @Resource
    private GroupService groupService;


    @Override
    public TUser login(TUser loginUser) {
        TUserExample tUserExample = new TUserExample();

        TUserExample.Criteria criteriaNumLogin = tUserExample.or();
        criteriaNumLogin.andUserNumEqualTo(loginUser.getUserNum()).andPasswordEqualTo(loginUser.getPassword());

        //匹配电话号码登录
        if(null!=loginUser.getPhoneNum()){
            TUserExample.Criteria criteriaPhoneLogin = tUserExample.or();
            criteriaPhoneLogin.andPhoneNumEqualTo(loginUser.getPhoneNum());
            criteriaPhoneLogin.andPasswordEqualTo(loginUser.getPassword());
        }



        //匹配邮箱密码登录
        if(null!=loginUser.getEmail()){
            TUserExample.Criteria criteriaEmailLogin = tUserExample.or();
            criteriaEmailLogin.andEmailEqualTo(loginUser.getEmail());
            criteriaEmailLogin.andPasswordEqualTo(loginUser.getPassword());
        }

        List<TUser> list = tUserMapper.selectByExample(tUserExample);
        if(list!=null && 0<list.size()){
            return list.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<FriendListInfo> getAllFriend(Integer userId) {
        FriendListInfoExample friendListInfoExample = new FriendListInfoExample();
        friendListInfoExample.createCriteria().andUserIdEqualTo(userId);
        return friendListInfoMapper.selectByExample(friendListInfoExample);
    }

    @Override
    public List<TUser> getTUserByUserNumAndAgeAndSex(TUser tUser, Integer ageBegin, Integer ageEnd) {
        TUserExample tUserExample = new TUserExample();
        if(tUser.getAge() != null && (tUser.getSex() != null && !tUser.getSex().equals("")) ){
            tUserExample.or().andAgeBetween(ageBegin, ageEnd).andSexEqualTo(tUser.getSex()).andUserNumEqualTo(tUser.getUserNum());
        }
        else if(tUser.getAge() == null && (tUser.getSex() == null || tUser.getSex().equals(""))){
            tUserExample.or().andUserNumEqualTo(tUser.getUserNum());
        }
        else if(tUser.getAge() == null){
            tUserExample.or().andUserNumEqualTo(tUser.getUserNum()).andSexEqualTo(tUser.getSex());
        }
        else if((tUser.getSex() == null || tUser.getSex().equals(""))){
            tUserExample.or().andUserNumEqualTo(tUser.getUserNum()).andAgeBetween(ageBegin, ageEnd);
        }
        return tUserMapper.selectByExample(tUserExample);
    }

    @Override
    public List<TUser> getTUserByNickNameAndAgeAndSex(TUser tUser, Integer ageBegin, Integer ageEnd) {
        TUserExample tUserExample = new TUserExample();
        if(tUser.getAge() != null && (tUser.getSex() != null && !tUser.getSex().equals(""))){
            tUserExample.or().andAgeBetween(ageBegin, ageEnd).andSexEqualTo(tUser.getSex()).andNickNameEqualTo(tUser.getNickName());
        }
        else if(tUser.getAge() == null && (tUser.getSex() == null || tUser.getSex().equals(""))){
            tUserExample.or().andNickNameEqualTo(tUser.getNickName());
        }
        else if(tUser.getAge() == null){
            tUserExample.or().andNickNameEqualTo(tUser.getNickName()).andSexEqualTo(tUser.getSex());
        }
        else if((tUser.getSex() == null || tUser.getSex().equals(""))){
            tUserExample.or().andNickNameEqualTo(tUser.getNickName()).andAgeBetween(ageBegin, ageEnd);
        }
        return tUserMapper.selectByExample(tUserExample);
    }

    @Override
    public void agreeAddGroup(TUser tUser, String toUserNum) {
        UnreadMessageExample unreadMessageExample = new UnreadMessageExample();
        unreadMessageExample.createCriteria().andFromUserNumEqualTo(toUserNum).andToUserNumEqualTo(tUser.getUserNum());
        List<UnreadMessage> list = unreadMessageMapper.selectByExample(unreadMessageExample);
        if(list!=null && list.size()>0){
            unreadMessageMapper.deleteByExample(unreadMessageExample);
            groupService.addMember(toUserNum,tUser.getUserId());
        }
        else{
            return;
        }
    }

    public void refuseAddGroup(TUser tUser,String toUserNum){
        UnreadMessageExample unreadMessageExample = new UnreadMessageExample();
        unreadMessageExample.createCriteria().andFromUserNumEqualTo(toUserNum).andToUserNumEqualTo(tUser.getUserNum());
        unreadMessageMapper.deleteByExample(unreadMessageExample);
    }
}
