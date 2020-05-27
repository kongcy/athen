package com.athen.mongdb.util;

import com.athen.mongdb.model.MapVo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HQL解析工具
 * User: chenying
 * Date: 2019-08-08
 * Time: 18:35
 * since: 1.0.0
 */
public abstract class HQLParseUtils {


    /*操作符*/
    public final static String OPERATORS_AND = "&";
    public final static String OPERATORS_OR = "|";


    /*查询符*/
    public final static String FINDS_GT = ">";
    public final static String FINDS_LT = "<";
    public final static String FINDS_IS = "=";
    public final static String FINDS_GTE = ">=";
    public final static String FINDS_LTE = "<=";
    public final static String FINDS_NE = "!=";
    public final static String FINDS_REGEX = "%";


    private final static String OPERATORS_PATTERN = "&|\\|";

    /**
     * 根据hql解析出操作符
     */
    public static List<String> getOperators(String hql) {
        Pattern pattern = Pattern.compile(OPERATORS_PATTERN);
        //匹配操作符
        Matcher matcher = pattern.matcher(hql);
        List<String> operators = Lists.newArrayList();
        while (matcher.find()) {
            operators.add(matcher.group().trim());
        }
        return operators;
    }

    private final static String FINDS_PATTERN = ">=|<=|>|<|!=|=|%";

    /**
     * 根据hql解析出查询符
     */
    public static List<String> getFinds(String hql) {
        Pattern pattern = Pattern.compile(FINDS_PATTERN);
        Matcher matcher = pattern.matcher(hql);
        List<String> finds = Lists.newArrayList();
        while (matcher.find()) {
            finds.add(matcher.group().trim());
        }
        return finds;
    }


    /**
     * 根据hql解析查询出来的键值对
     */
    public static Map<String, String> getMaps(String hql) {
        Map<String,String> map = Maps.newLinkedHashMap();
        /*根据操作符进行拆分  a=b| c=d -->[a=b,c=d]*/
        Iterable<String> values = Splitter.onPattern(OPERATORS_PATTERN).
                omitEmptyStrings().trimResults().split(hql);
        for (String value : values) {
            String[] result = value.split(FINDS_PATTERN);
            map.put(result[0], result[1]);
        }
        return map;
    }

    /**
     * 根据hql解析查询出来的键值对象
     * @return
     */
    public static List<MapVo> getMapVo(String hql) {
//        Map<String,String> map = Maps.newLinkedHashMap();
        List<MapVo> mapvolst = new ArrayList<MapVo>();
        /*根据操作符进行拆分  a=b| c=d -->[a=b,c=d]*/
        Iterable<String> values = Splitter.onPattern(OPERATORS_PATTERN).
                omitEmptyStrings().trimResults().split(hql);
        for (String value : values) {
            MapVo  mapvo = new MapVo();
            String[] result = value.split(FINDS_PATTERN);
            mapvo.setKey(result[0]);
            mapvo.setValue(result[1]);
            mapvolst.add(mapvo);
        }
        return mapvolst;
    }
}
