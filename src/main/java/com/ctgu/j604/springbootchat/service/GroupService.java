package com.ctgu.j604.springbootchat.service;

import com.ctgu.j604.springbootchat.model.GroupAndUser;

import java.util.List;

public interface GroupService {
    List<GroupAndUser> getAllGroup(Integer userId);
    List<String> getAllUsersByGroupNum(String groupNum);
}
