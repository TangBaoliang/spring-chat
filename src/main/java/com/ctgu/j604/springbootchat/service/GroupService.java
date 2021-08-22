package com.ctgu.j604.springbootchat.service;

import com.ctgu.j604.springbootchat.model.GroupAndUser;
import com.ctgu.j604.springbootchat.model.TUser;

import java.util.List;

public interface GroupService {
    List<GroupAndUser> getAllGroup(Integer userId);
    List<String> getAllUsersByGroupNum(String groupNum);
    String getMemberCommentByNumAndUserId(String groupNum, Integer userId);
    String createGroup(String groupName, TUser tUser);
    boolean checkGroupIfExist(String groupNum);
    boolean addMember(String groupNum, Integer userId);
}
