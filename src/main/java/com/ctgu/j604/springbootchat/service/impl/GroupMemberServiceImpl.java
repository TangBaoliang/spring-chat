package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.GroupAndUserMapper;
import com.ctgu.j604.springbootchat.model.GroupAndUser;
import com.ctgu.j604.springbootchat.model.GroupAndUserExample;
import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.service.GroupMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {
    @Resource
    private GroupAndUserMapper groupAndUserMapper;

    @Override
    public boolean modifyGroupMemberComment(TUser member, String groupNum, String newGroupComment) {
        GroupAndUserExample groupAndUserExample = new GroupAndUserExample();
        groupAndUserExample.createCriteria().andUserIdEqualTo(member.getUserId()).andGroupNumEqualTo(groupNum);
        if(newGroupComment.equals("")||newGroupComment==null){
            newGroupComment = member.getNickName();
        }
        GroupAndUser groupAndUser = new GroupAndUser();
        groupAndUser.setMemberComment(newGroupComment);
        if(groupAndUserMapper.updateByExampleSelective(groupAndUser,groupAndUserExample)>0){
            return true;
        }
        return false;
    }
}
