package com.athen.mongdb.service;

import com.mongodb.BasicDBList;
import com.mongodb.WriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-07-19
 * Time: 16:20
 * since: 1.0.0
 */
public interface IMongodbBaseDAO<E> {
    /**
     * 查询所有记录数
     */
    Long findCount();

    /**
     * 查询满足当前条件的
     */
    Long findCount(String hql);

    /**
     * 查询满足当前条件的记录，以collectionName传参查询
     */
    Long findCountWithCollectionName(String hql, String collectionName);

    /**
     * 新增记录
     */
    void insert(E e);

    /**
     * 新增记录,在类名首字母是大写的collection 上新增
     * vo 类
     */
    void insert(E e, Class clazz);

    /**
     * 新增记录，新增加后返回对象，主要是为了获取id
     */
    E insertAndReturnObject(E e);

    /**
     * 查询表格所有的数据
     */
    List<E> findAll();

    /**
     * 查询表格所有的数据,使用collectionName
     */
    List<E> findAll(String collectionName);

    /**
     * 符合条件的某一条数据
     */
    E findOne(String hql);

    /**
     * 根据分页+条件获取对应的实体集合
     */
    List<E> findListByPage(String hql, int pageIndex, int pageSize);

    /**
     * 根据分页+条件获取对应的实体集合,支持排序
     */
    List<E> findListByPage(String hql, List<Sort.Order> orders, int pageIndex,
                           int pageSize);

    /**
     * 根据分页+条件+collectionName获取对应的实体集合,支持排序
     */
    public List<E> findListByPageWithCollectionName(String hql, List<Sort.Order> orders, int pageIndex, int pageSize, String collectionName);

    /**
     * 根据分页获取对应的实体集合
     */
    List<E> findListByPage(int pageIndex, int pageSize);

    /**
     * 根据分页获取对应的实体集合,支持排序
     */
    List<E> findListByPage(int pageIndex, int pageSize, List<Sort.Order> orders);

    /**
     * 更新满足条件的第一个记录
     * @return
     */
    UpdateResult updateOne(String hql, Map<String, Object> updateMap);

    /**
     * 更新满足条件的第一个记录,在类名首字母是大写的collection 上更新
     * class vo类
     */
    UpdateResult updateOne(String hql, Map<String, Object> updateMap, Class clazz);

    /**
     * 更新满足条件的所有记录
     */
    UpdateResult updateAll(String hql, Map<String, Object> updateMap);

    /**
     * 更新满足条件的所有记录
     * class vo类
     */
    UpdateResult updateAll(String hql, Map<String, Object> updateMap, Class clazz);

    /**
     * 删除符合条件的数据
     */
    DeleteResult remove(String hql);

    /**
     * 删除符合条件的数据,在类名首字母是大写的collection 上删除
     * Class  vo 类
     */
    DeleteResult remove(String hql, Class clazz);

    /**
     * 删除所有记录
     */
    void drop();

    /**
     * Group by 查询
     *
     * @param e
     * @param hql                 查询条件，like String hql="startTime>=1469068943761 & endTime<=1469068943971 & title=传说在遥远的地方1 & site=infoq";
     * @param initalDocumentParam 初始化参数, like : {count:0}
     * @param reduceFunctionParam 处理函数, js 脚本, like : function(key, values){values.count+=1;}
     * @param keys                需要goup by的字段，可多个, like : "referrer","title"
     * @return
     */
    public BasicDBList findWithGroup(E e, String hql, String initalDocumentParam, String reduceFunctionParam,
                                     String... keys);

    /**
     * Group by 查询，支持使用collectName首字母大写
     *
     * @param e
     * @param isFirstLetterUppercase collectName是否第一个字母大写
     * @param hql                    查询条件，like String hql="startTime>=1469068943761 & endTime<=1469068943971 & title=传说在遥远的地方1 & site=infoq";
     * @param initalDocumentParam    初始化参数, like : {count:0}
     * @param reduceFunctionParam    处理函数, js 脚本, like : function(key, values){values.count+=1;}
     * @param keys                   需要goup by的字段，可多个, like : "referrer","title"
     * @return
     */
    public BasicDBList findWithGroup(E e, boolean isFirstLetterUppercase, String hql, String initalDocumentParam, String reduceFunctionParam,
                                     String... keys);

    /**
     * Aggregation 聚合查询，信息门户统计用
     *
     * @param startTime
     * @param endTime
     * @param title
     * @param site
     * @return
     */
    BasicDBList findWithAggregation4StatLog(long startTime, long endTime, String title, String site);

    /**
     * @param hql
     * @param collectionName，可选，如果不传，则按类名查
     * @return
     * @Description 按照集合名查询单笔数据
     * @author zoufeng@foresee.cn
     */
    E findOneWithCollectionName(String hql, String collectionName);

    /**
     * 根据hql有数据时更新，无数据时插入数据
     *
     * @param hql
     * @param updateMap
     * @return
     */
    WriteResult upsert(String hql, Map<String, Object> updateMap);

}
