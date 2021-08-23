package com.ctgu.j604.springbootchat.service;

import com.ctgu.j604.springbootchat.model.TUser;

public interface FriendService {
    boolean modifyFriendComment(TUser tUser, String toModifyFriendNum, String newComment);
}
