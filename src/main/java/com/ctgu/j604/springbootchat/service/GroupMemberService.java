package com.ctgu.j604.springbootchat.service;

import com.ctgu.j604.springbootchat.model.TUser;

public interface GroupMemberService {
    boolean modifyGroupMemberComment(TUser member,String groupNum, String newGroupComment);
}
