package com.ctgu.j604.springbootchat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnreadMessageExample implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public UnreadMessageExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andToUserNumIsNull() {
            addCriterion("to_user_num is null");
            return (Criteria) this;
        }

        public Criteria andToUserNumIsNotNull() {
            addCriterion("to_user_num is not null");
            return (Criteria) this;
        }

        public Criteria andToUserNumEqualTo(String value) {
            addCriterion("to_user_num =", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumNotEqualTo(String value) {
            addCriterion("to_user_num <>", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumGreaterThan(String value) {
            addCriterion("to_user_num >", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumGreaterThanOrEqualTo(String value) {
            addCriterion("to_user_num >=", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumLessThan(String value) {
            addCriterion("to_user_num <", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumLessThanOrEqualTo(String value) {
            addCriterion("to_user_num <=", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumLike(String value) {
            addCriterion("to_user_num like", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumNotLike(String value) {
            addCriterion("to_user_num not like", value, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumIn(List<String> values) {
            addCriterion("to_user_num in", values, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumNotIn(List<String> values) {
            addCriterion("to_user_num not in", values, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumBetween(String value1, String value2) {
            addCriterion("to_user_num between", value1, value2, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserNumNotBetween(String value1, String value2) {
            addCriterion("to_user_num not between", value1, value2, "toUserNum");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNull() {
            addCriterion("to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNotNull() {
            addCriterion("to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andToUserIdEqualTo(Integer value) {
            addCriterion("to_user_id =", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotEqualTo(Integer value) {
            addCriterion("to_user_id <>", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThan(Integer value) {
            addCriterion("to_user_id >", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_user_id >=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThan(Integer value) {
            addCriterion("to_user_id <", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_user_id <=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIn(List<Integer> values) {
            addCriterion("to_user_id in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotIn(List<Integer> values) {
            addCriterion("to_user_id not in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdBetween(Integer value1, Integer value2) {
            addCriterion("to_user_id between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_user_id not between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserNumIsNull() {
            addCriterion("from_user_num is null");
            return (Criteria) this;
        }

        public Criteria andFromUserNumIsNotNull() {
            addCriterion("from_user_num is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserNumEqualTo(String value) {
            addCriterion("from_user_num =", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumNotEqualTo(String value) {
            addCriterion("from_user_num <>", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumGreaterThan(String value) {
            addCriterion("from_user_num >", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumGreaterThanOrEqualTo(String value) {
            addCriterion("from_user_num >=", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumLessThan(String value) {
            addCriterion("from_user_num <", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumLessThanOrEqualTo(String value) {
            addCriterion("from_user_num <=", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumLike(String value) {
            addCriterion("from_user_num like", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumNotLike(String value) {
            addCriterion("from_user_num not like", value, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumIn(List<String> values) {
            addCriterion("from_user_num in", values, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumNotIn(List<String> values) {
            addCriterion("from_user_num not in", values, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumBetween(String value1, String value2) {
            addCriterion("from_user_num between", value1, value2, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserNumNotBetween(String value1, String value2) {
            addCriterion("from_user_num not between", value1, value2, "fromUserNum");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNull() {
            addCriterion("from_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNotNull() {
            addCriterion("from_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdEqualTo(Integer value) {
            addCriterion("from_user_id =", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotEqualTo(Integer value) {
            addCriterion("from_user_id <>", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThan(Integer value) {
            addCriterion("from_user_id >", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_user_id >=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThan(Integer value) {
            addCriterion("from_user_id <", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("from_user_id <=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIn(List<Integer> values) {
            addCriterion("from_user_id in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotIn(List<Integer> values) {
            addCriterion("from_user_id not in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdBetween(Integer value1, Integer value2) {
            addCriterion("from_user_id between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("from_user_id not between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeIsNull() {
            addCriterion("msg_type_code is null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeIsNotNull() {
            addCriterion("msg_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeEqualTo(Integer value) {
            addCriterion("msg_type_code =", value, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeNotEqualTo(Integer value) {
            addCriterion("msg_type_code <>", value, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeGreaterThan(Integer value) {
            addCriterion("msg_type_code >", value, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_type_code >=", value, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeLessThan(Integer value) {
            addCriterion("msg_type_code <", value, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeLessThanOrEqualTo(Integer value) {
            addCriterion("msg_type_code <=", value, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeIn(List<Integer> values) {
            addCriterion("msg_type_code in", values, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeNotIn(List<Integer> values) {
            addCriterion("msg_type_code not in", values, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeBetween(Integer value1, Integer value2) {
            addCriterion("msg_type_code between", value1, value2, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_type_code not between", value1, value2, "msgTypeCode");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumIsNull() {
            addCriterion("from_member_num is null");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumIsNotNull() {
            addCriterion("from_member_num is not null");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumEqualTo(String value) {
            addCriterion("from_member_num =", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumNotEqualTo(String value) {
            addCriterion("from_member_num <>", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumGreaterThan(String value) {
            addCriterion("from_member_num >", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumGreaterThanOrEqualTo(String value) {
            addCriterion("from_member_num >=", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumLessThan(String value) {
            addCriterion("from_member_num <", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumLessThanOrEqualTo(String value) {
            addCriterion("from_member_num <=", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumLike(String value) {
            addCriterion("from_member_num like", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumNotLike(String value) {
            addCriterion("from_member_num not like", value, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumIn(List<String> values) {
            addCriterion("from_member_num in", values, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumNotIn(List<String> values) {
            addCriterion("from_member_num not in", values, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumBetween(String value1, String value2) {
            addCriterion("from_member_num between", value1, value2, "fromMemberNum");
            return (Criteria) this;
        }

        public Criteria andFromMemberNumNotBetween(String value1, String value2) {
            addCriterion("from_member_num not between", value1, value2, "fromMemberNum");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table unread_message
     *
     * @mbg.generated do_not_delete_during_merge Thu Aug 19 19:01:48 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table unread_message
     *
     * @mbg.generated Thu Aug 19 19:01:48 CST 2021
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}