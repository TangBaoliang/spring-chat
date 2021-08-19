package com.ctgu.j604.springbootchat.service;

import com.ctgu.j604.springbootchat.model.FriendListInfo;
import com.ctgu.j604.springbootchat.model.TUser;

import java.util.List;

public interface TUserService {
    TUser login(TUser loginUser);
    List<FriendListInfo> getAllFriend(Integer userId);
}
