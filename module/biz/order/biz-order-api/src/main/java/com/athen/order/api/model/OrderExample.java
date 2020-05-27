package com.athen.order.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("`order_no` is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("`order_no` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("`order_no` =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("`order_no` <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("`order_no` >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("`order_no` >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("`order_no` <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("`order_no` <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("`order_no` like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("`order_no` not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("`order_no` in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("`order_no` not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("`order_no` between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("`order_no` not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("`member_id` is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("`member_id` is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Long value) {
            addCriterion("`member_id` =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Long value) {
            addCriterion("`member_id` <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Long value) {
            addCriterion("`member_id` >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`member_id` >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Long value) {
            addCriterion("`member_id` <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Long value) {
            addCriterion("`member_id` <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Long> values) {
            addCriterion("`member_id` in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Long> values) {
            addCriterion("`member_id` not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Long value1, Long value2) {
            addCriterion("`member_id` between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Long value1, Long value2) {
            addCriterion("`member_id` not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("`supplier_id` is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("`supplier_id` is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Long value) {
            addCriterion("`supplier_id` =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Long value) {
            addCriterion("`supplier_id` <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Long value) {
            addCriterion("`supplier_id` >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`supplier_id` >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Long value) {
            addCriterion("`supplier_id` <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Long value) {
            addCriterion("`supplier_id` <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Long> values) {
            addCriterion("`supplier_id` in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Long> values) {
            addCriterion("`supplier_id` not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Long value1, Long value2) {
            addCriterion("`supplier_id` between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Long value1, Long value2) {
            addCriterion("`supplier_id` not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNull() {
            addCriterion("`supplier_name` is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNotNull() {
            addCriterion("`supplier_name` is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameEqualTo(String value) {
            addCriterion("`supplier_name` =", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotEqualTo(String value) {
            addCriterion("`supplier_name` <>", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThan(String value) {
            addCriterion("`supplier_name` >", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThanOrEqualTo(String value) {
            addCriterion("`supplier_name` >=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThan(String value) {
            addCriterion("`supplier_name` <", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThanOrEqualTo(String value) {
            addCriterion("`supplier_name` <=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLike(String value) {
            addCriterion("`supplier_name` like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotLike(String value) {
            addCriterion("`supplier_name` not like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIn(List<String> values) {
            addCriterion("`supplier_name` in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotIn(List<String> values) {
            addCriterion("`supplier_name` not in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameBetween(String value1, String value2) {
            addCriterion("`supplier_name` between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotBetween(String value1, String value2) {
            addCriterion("`supplier_name` not between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("`order_status` is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("`order_status` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("`order_status` =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("`order_status` <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("`order_status` >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`order_status` >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("`order_status` <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`order_status` <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("`order_status` in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("`order_status` not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("`order_status` between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`order_status` not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIsNull() {
            addCriterion("`after_status` is null");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIsNotNull() {
            addCriterion("`after_status` is not null");
            return (Criteria) this;
        }

        public Criteria andAfterStatusEqualTo(Integer value) {
            addCriterion("`after_status` =", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotEqualTo(Integer value) {
            addCriterion("`after_status` <>", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusGreaterThan(Integer value) {
            addCriterion("`after_status` >", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`after_status` >=", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLessThan(Integer value) {
            addCriterion("`after_status` <", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`after_status` <=", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIn(List<Integer> values) {
            addCriterion("`after_status` in", values, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotIn(List<Integer> values) {
            addCriterion("`after_status` not in", values, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusBetween(Integer value1, Integer value2) {
            addCriterion("`after_status` between", value1, value2, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`after_status` not between", value1, value2, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalIsNull() {
            addCriterion("`product_amount_total` is null");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalIsNotNull() {
            addCriterion("`product_amount_total` is not null");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalEqualTo(Long value) {
            addCriterion("`product_amount_total` =", value, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalNotEqualTo(Long value) {
            addCriterion("`product_amount_total` <>", value, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalGreaterThan(Long value) {
            addCriterion("`product_amount_total` >", value, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("`product_amount_total` >=", value, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalLessThan(Long value) {
            addCriterion("`product_amount_total` <", value, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalLessThanOrEqualTo(Long value) {
            addCriterion("`product_amount_total` <=", value, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalIn(List<Long> values) {
            addCriterion("`product_amount_total` in", values, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalNotIn(List<Long> values) {
            addCriterion("`product_amount_total` not in", values, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalBetween(Long value1, Long value2) {
            addCriterion("`product_amount_total` between", value1, value2, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andProductAmountTotalNotBetween(Long value1, Long value2) {
            addCriterion("`product_amount_total` not between", value1, value2, "productAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalIsNull() {
            addCriterion("`order_amount_total` is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalIsNotNull() {
            addCriterion("`order_amount_total` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalEqualTo(Long value) {
            addCriterion("`order_amount_total` =", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalNotEqualTo(Long value) {
            addCriterion("`order_amount_total` <>", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalGreaterThan(Long value) {
            addCriterion("`order_amount_total` >", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("`order_amount_total` >=", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalLessThan(Long value) {
            addCriterion("`order_amount_total` <", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalLessThanOrEqualTo(Long value) {
            addCriterion("`order_amount_total` <=", value, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalIn(List<Long> values) {
            addCriterion("`order_amount_total` in", values, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalNotIn(List<Long> values) {
            addCriterion("`order_amount_total` not in", values, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalBetween(Long value1, Long value2) {
            addCriterion("`order_amount_total` between", value1, value2, "orderAmountTotal");
            return (Criteria) this;
        }

        public Criteria andOrderAmountTotalNotBetween(Long value1, Long value2) {
            addCriterion("`order_amount_total` not between", value1, value2, "orderAmountTotal");
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

        public Criteria andLogisticsFeeEqualTo(Long value) {
            addCriterion("`logistics_fee` =", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeNotEqualTo(Long value) {
            addCriterion("`logistics_fee` <>", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeGreaterThan(Long value) {
            addCriterion("`logistics_fee` >", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("`logistics_fee` >=", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeLessThan(Long value) {
            addCriterion("`logistics_fee` <", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeLessThanOrEqualTo(Long value) {
            addCriterion("`logistics_fee` <=", value, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeIn(List<Long> values) {
            addCriterion("`logistics_fee` in", values, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeNotIn(List<Long> values) {
            addCriterion("`logistics_fee` not in", values, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeBetween(Long value1, Long value2) {
            addCriterion("`logistics_fee` between", value1, value2, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andLogisticsFeeNotBetween(Long value1, Long value2) {
            addCriterion("`logistics_fee` not between", value1, value2, "logisticsFee");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("`address` is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("`address` is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("`address` =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("`address` <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("`address` >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("`address` >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("`address` <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("`address` <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("`address` like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("`address` not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("`address` in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("`address` not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("`address` between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("`address` not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNull() {
            addCriterion("`pay_channel` is null");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNotNull() {
            addCriterion("`pay_channel` is not null");
            return (Criteria) this;
        }

        public Criteria andPayChannelEqualTo(Integer value) {
            addCriterion("`pay_channel` =", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotEqualTo(Integer value) {
            addCriterion("`pay_channel` <>", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThan(Integer value) {
            addCriterion("`pay_channel` >", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("`pay_channel` >=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThan(Integer value) {
            addCriterion("`pay_channel` <", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThanOrEqualTo(Integer value) {
            addCriterion("`pay_channel` <=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelIn(List<Integer> values) {
            addCriterion("`pay_channel` in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotIn(List<Integer> values) {
            addCriterion("`pay_channel` not in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelBetween(Integer value1, Integer value2) {
            addCriterion("`pay_channel` between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("`pay_channel` not between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNull() {
            addCriterion("`trade_no` is null");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNotNull() {
            addCriterion("`trade_no` is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNoEqualTo(String value) {
            addCriterion("`trade_no` =", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotEqualTo(String value) {
            addCriterion("`trade_no` <>", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThan(String value) {
            addCriterion("`trade_no` >", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("`trade_no` >=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThan(String value) {
            addCriterion("`trade_no` <", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThanOrEqualTo(String value) {
            addCriterion("`trade_no` <=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLike(String value) {
            addCriterion("`trade_no` like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotLike(String value) {
            addCriterion("`trade_no` not like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoIn(List<String> values) {
            addCriterion("`trade_no` in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotIn(List<String> values) {
            addCriterion("`trade_no` not in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoBetween(String value1, String value2) {
            addCriterion("`trade_no` between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotBetween(String value1, String value2) {
            addCriterion("`trade_no` not between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("`pay_time` is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("`pay_time` is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("`pay_time` =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("`pay_time` <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("`pay_time` >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`pay_time` >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("`pay_time` <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("`pay_time` <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("`pay_time` in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("`pay_time` not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("`pay_time` between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("`pay_time` not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("`delivery_time` is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("`delivery_time` is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterion("`delivery_time` =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterion("`delivery_time` <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterion("`delivery_time` >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`delivery_time` >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterion("`delivery_time` <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("`delivery_time` <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterion("`delivery_time` in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterion("`delivery_time` not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("`delivery_time` between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("`delivery_time` not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("`finish_time` is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("`finish_time` is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("`finish_time` =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("`finish_time` <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("`finish_time` >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`finish_time` >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("`finish_time` <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("`finish_time` <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("`finish_time` in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("`finish_time` not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("`finish_time` between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("`finish_time` not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusIsNull() {
            addCriterion("`order_settlement_status` is null");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusIsNotNull() {
            addCriterion("`order_settlement_status` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusEqualTo(Integer value) {
            addCriterion("`order_settlement_status` =", value, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusNotEqualTo(Integer value) {
            addCriterion("`order_settlement_status` <>", value, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusGreaterThan(Integer value) {
            addCriterion("`order_settlement_status` >", value, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`order_settlement_status` >=", value, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusLessThan(Integer value) {
            addCriterion("`order_settlement_status` <", value, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`order_settlement_status` <=", value, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusIn(List<Integer> values) {
            addCriterion("`order_settlement_status` in", values, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusNotIn(List<Integer> values) {
            addCriterion("`order_settlement_status` not in", values, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusBetween(Integer value1, Integer value2) {
            addCriterion("`order_settlement_status` between", value1, value2, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`order_settlement_status` not between", value1, value2, "orderSettlementStatus");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeIsNull() {
            addCriterion("`order_settlement_time` is null");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeIsNotNull() {
            addCriterion("`order_settlement_time` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeEqualTo(Date value) {
            addCriterion("`order_settlement_time` =", value, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeNotEqualTo(Date value) {
            addCriterion("`order_settlement_time` <>", value, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeGreaterThan(Date value) {
            addCriterion("`order_settlement_time` >", value, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`order_settlement_time` >=", value, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeLessThan(Date value) {
            addCriterion("`order_settlement_time` <", value, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeLessThanOrEqualTo(Date value) {
            addCriterion("`order_settlement_time` <=", value, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeIn(List<Date> values) {
            addCriterion("`order_settlement_time` in", values, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeNotIn(List<Date> values) {
            addCriterion("`order_settlement_time` not in", values, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeBetween(Date value1, Date value2) {
            addCriterion("`order_settlement_time` between", value1, value2, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andOrderSettlementTimeNotBetween(Date value1, Date value2) {
            addCriterion("`order_settlement_time` not between", value1, value2, "orderSettlementTime");
            return (Criteria) this;
        }

        public Criteria andIsPackageIsNull() {
            addCriterion("`is_package` is null");
            return (Criteria) this;
        }

        public Criteria andIsPackageIsNotNull() {
            addCriterion("`is_package` is not null");
            return (Criteria) this;
        }

        public Criteria andIsPackageEqualTo(Integer value) {
            addCriterion("`is_package` =", value, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageNotEqualTo(Integer value) {
            addCriterion("`is_package` <>", value, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageGreaterThan(Integer value) {
            addCriterion("`is_package` >", value, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageGreaterThanOrEqualTo(Integer value) {
            addCriterion("`is_package` >=", value, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageLessThan(Integer value) {
            addCriterion("`is_package` <", value, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageLessThanOrEqualTo(Integer value) {
            addCriterion("`is_package` <=", value, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageIn(List<Integer> values) {
            addCriterion("`is_package` in", values, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageNotIn(List<Integer> values) {
            addCriterion("`is_package` not in", values, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageBetween(Integer value1, Integer value2) {
            addCriterion("`is_package` between", value1, value2, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsPackageNotBetween(Integer value1, Integer value2) {
            addCriterion("`is_package` not between", value1, value2, "isPackage");
            return (Criteria) this;
        }

        public Criteria andIsIntegralIsNull() {
            addCriterion("`is_integral` is null");
            return (Criteria) this;
        }

        public Criteria andIsIntegralIsNotNull() {
            addCriterion("`is_integral` is not null");
            return (Criteria) this;
        }

        public Criteria andIsIntegralEqualTo(Integer value) {
            addCriterion("`is_integral` =", value, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralNotEqualTo(Integer value) {
            addCriterion("`is_integral` <>", value, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralGreaterThan(Integer value) {
            addCriterion("`is_integral` >", value, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("`is_integral` >=", value, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralLessThan(Integer value) {
            addCriterion("`is_integral` <", value, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("`is_integral` <=", value, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralIn(List<Integer> values) {
            addCriterion("`is_integral` in", values, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralNotIn(List<Integer> values) {
            addCriterion("`is_integral` not in", values, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralBetween(Integer value1, Integer value2) {
            addCriterion("`is_integral` between", value1, value2, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andIsIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("`is_integral` not between", value1, value2, "isIntegral");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("`create_time` is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("`create_time` is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("`create_time` =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("`create_time` <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("`create_time` >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`create_time` >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("`create_time` <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`create_time` <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("`create_time` in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("`create_time` not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("`create_time` between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`create_time` not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("`update_time` is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("`update_time` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("`update_time` =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("`update_time` <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("`update_time` >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`update_time` >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("`update_time` <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`update_time` <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("`update_time` in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("`update_time` not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("`update_time` between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`update_time` not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNull() {
            addCriterion("`delete_time` is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNotNull() {
            addCriterion("`delete_time` is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeEqualTo(Date value) {
            addCriterion("`delete_time` =", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotEqualTo(Date value) {
            addCriterion("`delete_time` <>", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThan(Date value) {
            addCriterion("`delete_time` >", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`delete_time` >=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThan(Date value) {
            addCriterion("`delete_time` <", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("`delete_time` <=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIn(List<Date> values) {
            addCriterion("`delete_time` in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotIn(List<Date> values) {
            addCriterion("`delete_time` not in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeBetween(Date value1, Date value2) {
            addCriterion("`delete_time` between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("`delete_time` not between", value1, value2, "deleteTime");
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