package com.ctgu.j604.springbootchat.model;

public class FriendListInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_list_info.user_id
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_list_info.friend_id
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    private Integer friendId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_list_info.comment_for_friend
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    private String commentForFriend;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_list_info.user_num
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    private String userNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column friend_list_info.nick_name
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    private String nickName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_list_info.user_id
     *
     * @return the value of friend_list_info.user_id
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_list_info.user_id
     *
     * @param userId the value for friend_list_info.user_id
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_list_info.friend_id
     *
     * @return the value of friend_list_info.friend_id
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public Integer getFriendId() {
        return friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_list_info.friend_id
     *
     * @param friendId the value for friend_list_info.friend_id
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_list_info.comment_for_friend
     *
     * @return the value of friend_list_info.comment_for_friend
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public String getCommentForFriend() {
        return commentForFriend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_list_info.comment_for_friend
     *
     * @param commentForFriend the value for friend_list_info.comment_for_friend
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public void setCommentForFriend(String commentForFriend) {
        this.commentForFriend = commentForFriend == null ? null : commentForFriend.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_list_info.user_num
     *
     * @return the value of friend_list_info.user_num
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public String getUserNum() {
        return userNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_list_info.user_num
     *
     * @param userNum the value for friend_list_info.user_num
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column friend_list_info.nick_name
     *
     * @return the value of friend_list_info.nick_name
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column friend_list_info.nick_name
     *
     * @param nickName the value for friend_list_info.nick_name
     *
     * @mbg.generated Tue Aug 17 11:14:25 CST 2021
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }
}