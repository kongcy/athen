package com.athen.order.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderReturnsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderReturnsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andReturnsNoIsNull() {
            addCriterion("`returns_no` is null");
            return (Criteria) this;
        }

        public Criteria andReturnsNoIsNotNull() {
            addCriterion("`returns_no` is not null");
            return (Criteria) this;
        }

        public Criteria andReturnsNoEqualTo(String value) {
            addCriterion("`returns_no` =", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoNotEqualTo(String value) {
            addCriterion("`returns_no` <>", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoGreaterThan(String value) {
            addCriterion("`returns_no` >", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoGreaterThanOrEqualTo(String value) {
            addCriterion("`returns_no` >=", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoLessThan(String value) {
            addCriterion("`returns_no` <", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoLessThanOrEqualTo(String value) {
            addCriterion("`returns_no` <=", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoLike(String value) {
            addCriterion("`returns_no` like", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoNotLike(String value) {
            addCriterion("`returns_no` not like", value, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoIn(List<String> values) {
            addCriterion("`returns_no` in", values, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoNotIn(List<String> values) {
            addCriterion("`returns_no` not in", values, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoBetween(String value1, String value2) {
            addCriterion("`returns_no` between", value1, value2, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andReturnsNoNotBetween(String value1, String value2) {
            addCriterion("`returns_no` not between", value1, value2, "returnsNo");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("`order_id` is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("`order_id` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("`order_id` =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("`order_id` <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("`order_id` >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`order_id` >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("`order_id` <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("`order_id` <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("`order_id` in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("`order_id` not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("`order_id` between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("`order_id` not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("`express_no` is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("`express_no` is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("`express_no` =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("`express_no` <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("`express_no` >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("`express_no` >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("`express_no` <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("`express_no` <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("`express_no` like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("`express_no` not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("`express_no` in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("`express_no` not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("`express_no` between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("`express_no` not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameIsNull() {
            addCriterion("`consignee_realname` is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameIsNotNull() {
            addCriterion("`consignee_realname` is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameEqualTo(String value) {
            addCriterion("`consignee_realname` =", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameNotEqualTo(String value) {
            addCriterion("`consignee_realname` <>", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameGreaterThan(String value) {
            addCriterion("`consignee_realname` >", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("`consignee_realname` >=", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameLessThan(String value) {
            addCriterion("`consignee_realname` <", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameLessThanOrEqualTo(String value) {
            addCriterion("`consignee_realname` <=", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameLike(String value) {
            addCriterion("`consignee_realname` like", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameNotLike(String value) {
            addCriterion("`consignee_realname` not like", value, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameIn(List<String> values) {
            addCriterion("`consignee_realname` in", values, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameNotIn(List<String> values) {
            addCriterion("`consignee_realname` not in", values, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameBetween(String value1, String value2) {
            addCriterion("`consignee_realname` between", value1, value2, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeRealnameNotBetween(String value1, String value2) {
            addCriterion("`consignee_realname` not between", value1, value2, "consigneeRealname");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneIsNull() {
            addCriterion("`consignee_telphone` is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneIsNotNull() {
            addCriterion("`consignee_telphone` is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneEqualTo(String value) {
            addCriterion("`consignee_telphone` =", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneNotEqualTo(String value) {
            addCriterion("`consignee_telphone` <>", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneGreaterThan(String value) {
            addCriterion("`consignee_telphone` >", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneGreaterThanOrEqualTo(String value) {
            addCriterion("`consignee_telphone` >=", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneLessThan(String value) {
            addCriterion("`consignee_telphone` <", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneLessThanOrEqualTo(String value) {
            addCriterion("`consignee_telphone` <=", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneLike(String value) {
            addCriterion("`consignee_telphone` like", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneNotLike(String value) {
            addCriterion("`consignee_telphone` not like", value, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneIn(List<String> values) {
            addCriterion("`consignee_telphone` in", values, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneNotIn(List<String> values) {
            addCriterion("`consignee_telphone` not in", values, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneBetween(String value1, String value2) {
            addCriterion("`consignee_telphone` between", value1, value2, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphoneNotBetween(String value1, String value2) {
            addCriterion("`consignee_telphone` not between", value1, value2, "consigneeTelphone");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2IsNull() {
            addCriterion("`consignee_telphone2` is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2IsNotNull() {
            addCriterion("`consignee_telphone2` is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2EqualTo(String value) {
            addCriterion("`consignee_telphone2` =", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2NotEqualTo(String value) {
            addCriterion("`consignee_telphone2` <>", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2GreaterThan(String value) {
            addCriterion("`consignee_telphone2` >", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2GreaterThanOrEqualTo(String value) {
            addCriterion("`consignee_telphone2` >=", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2LessThan(String value) {
            addCriterion("`consignee_telphone2` <", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2LessThanOrEqualTo(String value) {
            addCriterion("`consignee_telphone2` <=", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2Like(String value) {
            addCriterion("`consignee_telphone2` like", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2NotLike(String value) {
            addCriterion("`consignee_telphone2` not like", value, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2In(List<String> values) {
            addCriterion("`consignee_telphone2` in", values, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2NotIn(List<String> values) {
            addCriterion("`consignee_telphone2` not in", values, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2Between(String value1, String value2) {
            addCriterion("`consignee_telphone2` between", value1, value2, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeTelphone2NotBetween(String value1, String value2) {
            addCriterion("`consignee_telphone2` not between", value1, value2, "consigneeTelphone2");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressIsNull() {
            addCriterion("`consignee_address` is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressIsNotNull() {
            addCriterion("`consignee_address` is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressEqualTo(String value) {
            addCriterion("`consignee_address` =", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotEqualTo(String value) {
            addCriterion("`consignee_address` <>", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressGreaterThan(String value) {
            addCriterion("`consignee_address` >", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("`consignee_address` >=", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressLessThan(String value) {
            addCriterion("`consignee_address` <", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressLessThanOrEqualTo(String value) {
            addCriterion("`consignee_address` <=", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressLike(String value) {
            addCriterion("`consignee_address` like", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotLike(String value) {
            addCriterion("`consignee_address` not like", value, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressIn(List<String> values) {
            addCriterion("`consignee_address` in", values, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotIn(List<String> values) {
            addCriterion("`consignee_address` not in", values, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressBetween(String value1, String value2) {
            addCriterion("`consignee_address` between", value1, value2, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeAddressNotBetween(String value1, String value2) {
            addCriterion("`consignee_address` not between", value1, value2, "consigneeAddress");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipIsNull() {
            addCriterion("`consignee_zip` is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipIsNotNull() {
            addCriterion("`consignee_zip` is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipEqualTo(String value) {
            addCriterion("`consignee_zip` =", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipNotEqualTo(String value) {
            addCriterion("`consignee_zip` <>", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipGreaterThan(String value) {
            addCriterion("`consignee_zip` >", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipGreaterThanOrEqualTo(String value) {
            addCriterion("`consignee_zip` >=", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipLessThan(String value) {
            addCriterion("`consignee_zip` <", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipLessThanOrEqualTo(String value) {
            addCriterion("`consignee_zip` <=", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipLike(String value) {
            addCriterion("`consignee_zip` like", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipNotLike(String value) {
            addCriterion("`consignee_zip` not like", value, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipIn(List<String> values) {
            addCriterion("`consignee_zip` in", values, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipNotIn(List<String> values) {
            addCriterion("`consignee_zip` not in", values, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipBetween(String value1, String value2) {
            addCriterion("`consignee_zip` between", value1, value2, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andConsigneeZipNotBetween(String value1, String value2) {
            addCriterion("`consignee_zip` not between", value1, value2, "consigneeZip");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeIsNull() {
            addCriterion("`logistics_type` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeIsNotNull() {
            addCriterion("`logistics_type` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeEqualTo(String value) {
            addCriterion("`logistics_type` =", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeNotEqualTo(String value) {
            addCriterion("`logistics_type` <>", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeGreaterThan(String value) {
            addCriterion("`logistics_type` >", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`logistics_type` >=", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeLessThan(String value) {
            addCriterion("`logistics_type` <", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeLessThanOrEqualTo(String value) {
            addCriterion("`logistics_type` <=", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeLike(String value) {
            addCriterion("`logistics_type` like", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeNotLike(String value) {
            addCriterion("`logistics_type` not like", value, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeIn(List<String> values) {
            addCriterion("`logistics_type` in", values, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeNotIn(List<String> values) {
            addCriterion("`logistics_type` not in", values, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeBetween(String value1, String value2) {
            addCriterion("`logistics_type` between", value1, value2, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsTypeNotBetween(String value1, String value2) {
            addCriterion("`logistics_type` not between", value1, value2, "logisticsType");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeIsNull() {
            addCriterion("`logistics_fee` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeIsNotNull() {
            addCriterion("`logistics_fee` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeEqualTo(BigDecimal value) {
            addCriterion("`logistics_fee` =", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeNotEqualTo(BigDecimal value) {
            addCriterion("`logistics_fee` <>", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeGreaterThan(BigDecimal value) {
            addCriterion("`logistics_fee` >", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("`logistics_fee` >=", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeLessThan(BigDecimal value) {
            addCriterion("`logistics_fee` <", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("`logistics_fee` <=", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeIn(List<BigDecimal> values) {
            addCriterion("`logistics_fee` in", values, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeNotIn(List<BigDecimal> values) {
            addCriterion("`logistics_fee` not in", values, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`logistics_fee` between", value1, value2, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("`logistics_fee` not between", value1, value2, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusIsNull() {
            addCriterion("`order_logistics_status` is null");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusIsNotNull() {
            addCriterion("`order_logistics_status` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusEqualTo(Integer value) {
            addCriterion("`order_logistics_status` =", value, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusNotEqualTo(Integer value) {
            addCriterion("`order_logistics_status` <>", value, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusGreaterThan(Integer value) {
            addCriterion("`order_logistics_status` >", value, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`order_logistics_status` >=", value, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusLessThan(Integer value) {
            addCriterion("`order_logistics_status` <", value, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`order_logistics_status` <=", value, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusIn(List<Integer> values) {
            addCriterion("`order_logistics_status` in", values, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusNotIn(List<Integer> values) {
            addCriterion("`order_logistics_status` not in", values, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusBetween(Integer value1, Integer value2) {
            addCriterion("`order_logistics_status` between", value1, value2, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`order_logistics_status` not between", value1, value2, "orderLogisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusIsNull() {
            addCriterion("`logistics_settlement_status` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusIsNotNull() {
            addCriterion("`logistics_settlement_status` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusEqualTo(Integer value) {
            addCriterion("`logistics_settlement_status` =", value, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusNotEqualTo(Integer value) {
            addCriterion("`logistics_settlement_status` <>", value, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusGreaterThan(Integer value) {
            addCriterion("`logistics_settlement_status` >", value, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`logistics_settlement_status` >=", value, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusLessThan(Integer value) {
            addCriterion("`logistics_settlement_status` <", value, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`logistics_settlement_status` <=", value, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusIn(List<Integer> values) {
            addCriterion("`logistics_settlement_status` in", values, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusNotIn(List<Integer> values) {
            addCriterion("`logistics_settlement_status` not in", values, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusBetween(Integer value1, Integer value2) {
            addCriterion("`logistics_settlement_status` between", value1, value2, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`logistics_settlement_status` not between", value1, value2, "logisticsSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastIsNull() {
            addCriterion("`logistics_result_last` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastIsNotNull() {
            addCriterion("`logistics_result_last` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastEqualTo(String value) {
            addCriterion("`logistics_result_last` =", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastNotEqualTo(String value) {
            addCriterion("`logistics_result_last` <>", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastGreaterThan(String value) {
            addCriterion("`logistics_result_last` >", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastGreaterThanOrEqualTo(String value) {
            addCriterion("`logistics_result_last` >=", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastLessThan(String value) {
            addCriterion("`logistics_result_last` <", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastLessThanOrEqualTo(String value) {
            addCriterion("`logistics_result_last` <=", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastLike(String value) {
            addCriterion("`logistics_result_last` like", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastNotLike(String value) {
            addCriterion("`logistics_result_last` not like", value, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastIn(List<String> values) {
            addCriterion("`logistics_result_last` in", values, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastNotIn(List<String> values) {
            addCriterion("`logistics_result_last` not in", values, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastBetween(String value1, String value2) {
            addCriterion("`logistics_result_last` between", value1, value2, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLastNotBetween(String value1, String value2) {
            addCriterion("`logistics_result_last` not between", value1, value2, "logisticsResultLast");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultIsNull() {
            addCriterion("`logistics_result` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultIsNotNull() {
            addCriterion("`logistics_result` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultEqualTo(String value) {
            addCriterion("`logistics_result` =", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultNotEqualTo(String value) {
            addCriterion("`logistics_result` <>", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultGreaterThan(String value) {
            addCriterion("`logistics_result` >", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultGreaterThanOrEqualTo(String value) {
            addCriterion("`logistics_result` >=", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLessThan(String value) {
            addCriterion("`logistics_result` <", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLessThanOrEqualTo(String value) {
            addCriterion("`logistics_result` <=", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultLike(String value) {
            addCriterion("`logistics_result` like", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultNotLike(String value) {
            addCriterion("`logistics_result` not like", value, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultIn(List<String> values) {
            addCriterion("`logistics_result` in", values, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultNotIn(List<String> values) {
            addCriterion("`logistics_result` not in", values, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultBetween(String value1, String value2) {
            addCriterion("`logistics_result` between", value1, value2, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsResultNotBetween(String value1, String value2) {
            addCriterion("`logistics_result` not between", value1, value2, "logisticsResult");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeIsNull() {
            addCriterion("`logistics_create_time` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeIsNotNull() {
            addCriterion("`logistics_create_time` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeEqualTo(Date value) {
            addCriterion("`logistics_create_time` =", value, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeNotEqualTo(Date value) {
            addCriterion("`logistics_create_time` <>", value, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeGreaterThan(Date value) {
            addCriterion("`logistics_create_time` >", value, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`logistics_create_time` >=", value, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeLessThan(Date value) {
            addCriterion("`logistics_create_time` <", value, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`logistics_create_time` <=", value, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeIn(List<Date> values) {
            addCriterion("`logistics_create_time` in", values, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeNotIn(List<Date> values) {
            addCriterion("`logistics_create_time` not in", values, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeBetween(Date value1, Date value2) {
            addCriterion("`logistics_create_time` between", value1, value2, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`logistics_create_time` not between", value1, value2, "logisticsCreateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeIsNull() {
            addCriterion("`logistics_update_time` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeIsNotNull() {
            addCriterion("`logistics_update_time` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeEqualTo(Date value) {
            addCriterion("`logistics_update_time` =", value, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeNotEqualTo(Date value) {
            addCriterion("`logistics_update_time` <>", value, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeGreaterThan(Date value) {
            addCriterion("`logistics_update_time` >", value, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`logistics_update_time` >=", value, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeLessThan(Date value) {
            addCriterion("`logistics_update_time` <", value, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`logistics_update_time` <=", value, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeIn(List<Date> values) {
            addCriterion("`logistics_update_time` in", values, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeNotIn(List<Date> values) {
            addCriterion("`logistics_update_time` not in", values, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("`logistics_update_time` between", value1, value2, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`logistics_update_time` not between", value1, value2, "logisticsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeIsNull() {
            addCriterion("`logistics_settlement_time` is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeIsNotNull() {
            addCriterion("`logistics_settlement_time` is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeEqualTo(Date value) {
            addCriterion("`logistics_settlement_time` =", value, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeNotEqualTo(Date value) {
            addCriterion("`logistics_settlement_time` <>", value, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeGreaterThan(Date value) {
            addCriterion("`logistics_settlement_time` >", value, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`logistics_settlement_time` >=", value, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeLessThan(Date value) {
            addCriterion("`logistics_settlement_time` <", value, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeLessThanOrEqualTo(Date value) {
            addCriterion("`logistics_settlement_time` <=", value, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeIn(List<Date> values) {
            addCriterion("`logistics_settlement_time` in", values, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeNotIn(List<Date> values) {
            addCriterion("`logistics_settlement_time` not in", values, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeBetween(Date value1, Date value2) {
            addCriterion("`logistics_settlement_time` between", value1, value2, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsSettlementTimeNotBetween(Date value1, Date value2) {
            addCriterion("`logistics_settlement_time` not between", value1, value2, "logisticsSettlementTime");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeIsNull() {
            addCriterion("`returns_type` is null");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeIsNotNull() {
            addCriterion("`returns_type` is not null");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeEqualTo(Integer value) {
            addCriterion("`returns_type` =", value, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeNotEqualTo(Integer value) {
            addCriterion("`returns_type` <>", value, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeGreaterThan(Integer value) {
            addCriterion("`returns_type` >", value, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`returns_type` >=", value, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeLessThan(Integer value) {
            addCriterion("`returns_type` <", value, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`returns_type` <=", value, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeIn(List<Integer> values) {
            addCriterion("`returns_type` in", values, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeNotIn(List<Integer> values) {
            addCriterion("`returns_type` not in", values, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeBetween(Integer value1, Integer value2) {
            addCriterion("`returns_type` between", value1, value2, "returnsType");
            return (Criteria) this;
        }

        public Criteria andReturnsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`returns_type` not between", value1, value2, "returnsType");
            return (Criteria) this;
        }

        public Criteria andHandlingWayIsNull() {
            addCriterion("`handling_way` is null");
            return (Criteria) this;
        }

        public Criteria andHandlingWayIsNotNull() {
            addCriterion("`handling_way` is not null");
            return (Criteria) this;
        }

        public Criteria andHandlingWayEqualTo(Integer value) {
            addCriterion("`handling_way` =", value, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayNotEqualTo(Integer value) {
            addCriterion("`handling_way` <>", value, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayGreaterThan(Integer value) {
            addCriterion("`handling_way` >", value, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("`handling_way` >=", value, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayLessThan(Integer value) {
            addCriterion("`handling_way` <", value, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayLessThanOrEqualTo(Integer value) {
            addCriterion("`handling_way` <=", value, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayIn(List<Integer> values) {
            addCriterion("`handling_way` in", values, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayNotIn(List<Integer> values) {
            addCriterion("`handling_way` not in", values, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayBetween(Integer value1, Integer value2) {
            addCriterion("`handling_way` between", value1, value2, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andHandlingWayNotBetween(Integer value1, Integer value2) {
            addCriterion("`handling_way` not between", value1, value2, "handlingWay");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountIsNull() {
            addCriterion("`returns_amount` is null");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountIsNotNull() {
            addCriterion("`returns_amount` is not null");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountEqualTo(Long value) {
            addCriterion("`returns_amount` =", value, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountNotEqualTo(Long value) {
            addCriterion("`returns_amount` <>", value, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountGreaterThan(Long value) {
            addCriterion("`returns_amount` >", value, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("`returns_amount` >=", value, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountLessThan(Long value) {
            addCriterion("`returns_amount` <", value, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountLessThanOrEqualTo(Long value) {
            addCriterion("`returns_amount` <=", value, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountIn(List<Long> values) {
            addCriterion("`returns_amount` in", values, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountNotIn(List<Long> values) {
            addCriterion("`returns_amount` not in", values, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountBetween(Long value1, Long value2) {
            addCriterion("`returns_amount` between", value1, value2, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnsAmountNotBetween(Long value1, Long value2) {
            addCriterion("`returns_amount` not between", value1, value2, "returnsAmount");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeIsNull() {
            addCriterion("`return_submit_time` is null");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeIsNotNull() {
            addCriterion("`return_submit_time` is not null");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeEqualTo(Date value) {
            addCriterion("`return_submit_time` =", value, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeNotEqualTo(Date value) {
            addCriterion("`return_submit_time` <>", value, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeGreaterThan(Date value) {
            addCriterion("`return_submit_time` >", value, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`return_submit_time` >=", value, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeLessThan(Date value) {
            addCriterion("`return_submit_time` <", value, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("`return_submit_time` <=", value, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeIn(List<Date> values) {
            addCriterion("`return_submit_time` in", values, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeNotIn(List<Date> values) {
            addCriterion("`return_submit_time` not in", values, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("`return_submit_time` between", value1, value2, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andReturnSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("`return_submit_time` not between", value1, value2, "returnSubmitTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeIsNull() {
            addCriterion("`handling_time` is null");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeIsNotNull() {
            addCriterion("`handling_time` is not null");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeEqualTo(Date value) {
            addCriterion("`handling_time` =", value, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeNotEqualTo(Date value) {
            addCriterion("`handling_time` <>", value, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeGreaterThan(Date value) {
            addCriterion("`handling_time` >", value, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`handling_time` >=", value, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeLessThan(Date value) {
            addCriterion("`handling_time` <", value, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeLessThanOrEqualTo(Date value) {
            addCriterion("`handling_time` <=", value, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeIn(List<Date> values) {
            addCriterion("`handling_time` in", values, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeNotIn(List<Date> values) {
            addCriterion("`handling_time` not in", values, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeBetween(Date value1, Date value2) {
            addCriterion("`handling_time` between", value1, value2, "handlingTime");
            return (Criteria) this;
        }

        public Criteria andHandlingTimeNotBetween(Date value1, Date value2) {
            addCriterion("`handling_time` not between", value1, value2, "handlingTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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