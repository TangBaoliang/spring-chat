package com.ctgu.j604.springbootchat.model;

import java.util.Date;

public class LinkmanList extends LinkmanListKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column linkman_list.add_time
     *
     * @mbg.generated Sun Aug 22 16:06:14 CST 2021
     */
    private Date addTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column linkman_list.comment_for_friend
     *
     * @mbg.generated Sun Aug 22 16:06:14 CST 2021
     */
    private String commentForFriend;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column linkman_list.add_time
     *
     * @return the value of linkman_list.add_time
     *
     * @mbg.generated Sun Aug 22 16:06:14 CST 2021
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column linkman_list.add_time
     *
     * @param addTime the value for linkman_list.add_time
     *
     * @mbg.generated Sun Aug 22 16:06:14 CST 2021
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column linkman_list.comment_for_friend
     *
     * @return the value of linkman_list.comment_for_friend
     *
     * @mbg.generated Sun Aug 22 16:06:14 CST 2021
     */
    public String getCommentForFriend() {
        return commentForFriend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column linkman_list.comment_for_friend
     *
     * @param commentForFriend the value for linkman_list.comment_for_friend
     *
     * @mbg.generated Sun Aug 22 16:06:14 CST 2021
     */
    public void setCommentForFriend(String commentForFriend) {
        this.commentForFriend = commentForFriend == null ? null : commentForFriend.trim();
    }
}