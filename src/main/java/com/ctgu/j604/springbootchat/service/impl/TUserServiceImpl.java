package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.FriendListInfoMapper;
import com.ctgu.j604.springbootchat.mapper.TUserMapper;
import com.ctgu.j604.springbootchat.model.FriendListInfo;
import com.ctgu.j604.springbootchat.model.FriendListInfoExample;
import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.model.TUserExample;
import com.ctgu.j604.springbootchat.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private FriendListInfoMapper friendListInfoMapper;

    @Override
    public TUser login(TUser loginUser) {
        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andUserNumEqualTo(loginUser.getUserNum()).andPasswordEqualTo(loginUser.getPassword());
//        TUserExample.Criteria criteriaNumLogin = tUserExample.or();
//        criteriaNumLogin.andUserNumEqualTo(loginUser.getUserNum());
//        criteriaNumLogin.andPasswordEqualTo(loginUser.getPassword());




        //匹配电话号码登录
//        if(null!=loginUser.getPhoneNum()){
//            TUserExample.Criteria criteriaPhoneLogin = tUserExample.or();
//            criteriaPhoneLogin.an dPhoneNumEqualTo(loginUser.getPhoneNum());
//            criteriaPhoneLogin.andPasswordEqualTo(loginUser.getPassword());
//        }
//
//
//
//        //匹配邮箱密码登录
//        if(null!=loginUser.getEmail()){
//            TUserExample.Criteria criteriaEmailLogin = tUserExample.or();
//            criteriaEmailLogin.andEmailEqualTo(loginUser.getEmail());
//            criteriaEmailLogin.andPasswordEqualTo(loginUser.getPassword());
//        }

        List<TUser> list = tUserMapper.selectByExample(tUserExample);
        if(0!=list.size()){
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

}
