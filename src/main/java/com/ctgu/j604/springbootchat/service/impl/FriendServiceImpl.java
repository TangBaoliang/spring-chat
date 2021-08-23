package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.LinkmanListMapper;
import com.ctgu.j604.springbootchat.mapper.TUserMapper;
import com.ctgu.j604.springbootchat.model.LinkmanList;
import com.ctgu.j604.springbootchat.model.LinkmanListExample;
import com.ctgu.j604.springbootchat.model.TUser;
import com.ctgu.j604.springbootchat.model.TUserExample;
import com.ctgu.j604.springbootchat.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private LinkmanListMapper linkmanListMapper;

    @Override
    public boolean modifyFriendComment(TUser tUser, String toModifyFriendNum, String newComment) {
        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andUserNumEqualTo(toModifyFriendNum);
        List<TUser> list = tUserMapper.selectByExample(tUserExample);
        Integer toModifyFriendId = null;
        if(list!=null && list.size()>0){
            toModifyFriendId = list.get(0).getUserId();
        }
        else{
            return false;
        }
        LinkmanListExample linkmanListExample = new LinkmanListExample();
        linkmanListExample.createCriteria().andUserIdEqualTo(tUser.getUserId()).andFriendIdEqualTo(toModifyFriendId);

        List<LinkmanList> linkmanLists = linkmanListMapper.selectByExample(linkmanListExample);
        LinkmanList linkmanList = null;
        if(linkmanLists!=null && linkmanLists.size()>0){
            linkmanList = linkmanLists.get(0);
        }
        else{
            return false;
        }
        if(newComment==null || "".equals(newComment)){
            newComment = tUser.getNickName();
        }

        linkmanList.setCommentForFriend(newComment);
        if(linkmanListMapper.updateByPrimaryKeySelective(linkmanList)>0){
            return true;
        }
        return false;
    }
}
