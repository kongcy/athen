package com.athen.mongdb.service.impl;

import com.athen.core.date.DateFormatType;
import com.athen.core.date.DateUtil;
import com.athen.core.util.A;
import com.athen.core.util.U;
import com.athen.mongdb.model.MapVo;
import com.athen.mongdb.service.IMongodbBaseDAO;
import com.athen.mongdb.util.HQLParseUtils;
import com.athen.mongdb.util.ReflectUtils;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBList;
import com.mongodb.WriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Mongodb 服务类
 * User: chenying
 * Date: 2019-07-19
 * Time: 16:23
 * since: 1.0.0
 */
@Slf4j
public class MongodbBaseDAOExtImpl<E> implements IMongodbBaseDAO<E> {

    private static final Logger logger = LoggerFactory.getLogger(MongodbBaseDAOExtImpl.class);

    private MongoTemplate template;

    public void setTemplate(MongoTemplate template){
        this.template = template;
    }
    /**
     * Entity的类型
     */
    protected Class<E> entityClass;

    public MongodbBaseDAOExtImpl(MongoTemplate template) {
          this.template=template;
          this.entityClass = ReflectUtils.getSuperClassGenricType(getClass());
    }

    @Override
    public Long findCount() {
        logger.debug("查询对象:{}所有记录条数", entityClass);
        return template.count(new Query(), entityClass);
    }

    @Override
    public Long findCount(String hql) {
        logger.debug("查询对象:{}指定记录", entityClass);
        /* 创建查询语句 */
        Query query = buildQuery(hql);
        /* 返回查询结果 */
        return template.count(query, entityClass);
    }

    @Override
    public Long findCountWithCollectionName(String hql, String collectionName) {
        logger.debug("查询对象:{}指定记录", entityClass);
        /* 创建查询语句 */
        Query query = buildQuery(hql);
        /* 返回查询结果 */
        return template.count(query, entityClass, collectionName);
    }

    @Override
    public void insert(E e) {
    }

    @Override
    public E insertAndReturnObject(E e) {
        uniqueCheck(e);
        logger.debug("插入对象:{}", e);
        template.insert(e);
        return e;
    }

    /**
     * 数据唯一性检查
     */
    private void uniqueCheck(E e) {
        /*
        List<String> properties = ReflectUtils.containsAnnotation(entityClass, Unique.class);
        //不存在唯一性注解
        if (A.isEmpty(properties)) {
            return;
        }
        //单个注解
        if (CollectionUtils.isSingleton(properties)) {
            String property = properties.get(0);
            String value = (String) ReflectUtils.invokeGetterMethod(e, property);
            //已存在数据抛出异常
            E result = findOne(properties.get(0) + HQLParseUtils.FINDS_IS + value);
            if (U.isNotBlank(result)) {
                throw new RuntimeException(String.format("对象:%s已存属性%s 值为%s的记录,新增记录失败", entityClass, property, value));
            }
            return;
        }
        //多个注解
        StringBuffer stringBuffer = new StringBuffer();
        for (String property : properties) {
            String value = (String) ReflectUtils.invokeGetterMethod(e, property);
            stringBuffer.append(property).append(HQLParseUtils.FINDS_IS).append(value).append(HQLParseUtils.OPERATORS_AND);
        }
        String hql = StringUtils.substringEnd(stringBuffer.toString(), 1);//去除尾部操作符
        E result = findOne(hql);
        if (ObjectUtils.notEmpty(result)) {
            throw new RuntimeException(String.format("根据hql:%s查询对象%s已存在,新增记录失败", hql, entityClass));
        }
*/
    }

    @Override
    public List<E> findAll() {
        logger.debug("查询对象:{}所有记录条数", entityClass);
        return template.findAll(entityClass);
    }

    public List<E> findAll(String collectionName) {
        logger.debug("查询对象:{}，collectionName:{}所有记录条数", collectionName, entityClass);
        if (null == collectionName || "".equals(collectionName.trim())) {
            return template.findAll(entityClass);
        } else {
            return template.findAll(entityClass, collectionName);
        }
    }

    @Override
    public E findOne(String hql) {
        /* 创建查询语句 */
        Query query = buildQuery(hql);
        logger.debug("根据hql:{}查询对象:{}", hql, entityClass);
        /* 返回查询结果 */
        return template.findOne(query, entityClass);
    }

    @Override
    public E findOneWithCollectionName(String hql, String collectionName) {
        /* 创建查询语句 */
        Query query = buildQuery(hql);
        logger.debug("根据hql:{}查询对象:{}", hql, entityClass);
        /* 返回查询结果 */
        if (U.isNotBlank(collectionName)) {
            return template.findOne(query, entityClass, collectionName);
        }
        return this.findOne(hql);
    }

    @Override
    public WriteResult upsert(String hql, Map<String, Object> updateMap) {
        return null;
    }

    @Override
    public List<E> findListByPage(String hql, List<Sort.Order> orders, int pageIndex, int pageSize) {
        logger.debug("分页查询: hql:{},order:{},pageIndex:{},pageSize:{}", hql, orders, pageIndex, pageSize);
        Query query = buildQuery(hql, orders, pageIndex, pageSize);
        return template.find(query, entityClass);
    }

    @Override
    public List<E> findListByPageWithCollectionName(String hql, List<Sort.Order> orders, int pageIndex, int pageSize, String collectionName) {
        logger.debug("分页查询: hql:{},order:{},pageIndex:{},pageSize:{}", hql, orders, pageIndex, pageSize);
        Query query = buildQuery(hql, orders, pageIndex, pageSize);
        if (!(collectionName == null || "".equals(collectionName))) {
            return template.find(query, entityClass, collectionName);
        }
        return template.find(query, entityClass);
    }

    @Override
    public List<E> findListByPage(int pageIndex, int pageSize) {
        return this.findListByPage(null, null, pageIndex, pageSize);
    }

    @Override
    public List<E> findListByPage(int pageIndex, int pageSize, List<Sort.Order> orders) {
        return this.findListByPage(null, orders, pageIndex, pageSize);
    }

    @Override
    public List<E> findListByPage(String hql, int pageIndex, int pageSize) {
        return this.findListByPage(hql, null, pageIndex, pageSize);
    }

    @Override
    public UpdateResult updateOne(String hql, Map<String, Object> updateMap) {
        logger.debug("更新满足条件的第一个记录:{} ", entityClass);
        /* 构建查询语句 */
        Query query = buildQuery(hql);
        /* 执行更新 */
        return template.updateFirst(query, buildUpdate(updateMap), entityClass);
    }

    @Override
    public UpdateResult updateAll(String hql, Map<String, Object> updateMap) {
        logger.debug("更新满足条件的:{}所有记录", entityClass);
        /* 构建查询语句 */
        Query query = buildQuery(hql);
        /* 执行更新 */
        return template.updateMulti(query, buildUpdate(updateMap), entityClass);
    }

    @Override
    public DeleteResult remove(String hql) {
        logger.debug("删除满足条件的:{}所有记录 ", entityClass);
        /* 创建查询语句 */
        Query query = buildQuery(hql);
        /* 执行删除 */
        return template.remove(query, entityClass);
    }

    /**
     * 生成更新数据
     */
    private Update buildUpdate(Map<String, Object> updateMap) {
        Update update = new Update();
        for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        logger.debug("更新对象:{}", update);
        return update;
    }

    @Override
    public void drop() {
        logger.debug("删除所有对象记录");
        template.dropCollection(entityClass);
    }

    @Override
    public BasicDBList findWithGroup(E e, String hql, String initalDocumentParam, String reduceFunctionParam,
                                     String... keys) {

        if (StringUtils.isEmpty(initalDocumentParam)) {
            initalDocumentParam = "{count:0}";
        }

        if (StringUtils.isEmpty(reduceFunctionParam)) {
            reduceFunctionParam = "function(key, values){values.count+=1;}";
        }

        //首字母小写
        String simpleClassName = e.getClass().getSimpleName().substring(0, 1).toLowerCase() + e.getClass().getSimpleName().replaceFirst("\\w", "");

        //String hql="startTime>=1469068943761 & endTime<=1469068943971 & title=传说在遥远的地方1 & site=infoq";
        Criteria criteria = buildCriteria(hql);

        GroupByResults r1 = template.group(criteria, simpleClassName, GroupBy.key(keys)
                .initialDocument(initalDocumentParam)
                .reduceFunction(reduceFunctionParam), e.getClass());

		/*GroupBy groupBy1 = GroupBy.key(keys)
				.initialDocument(initalDocumentParam)
				.reduceFunction(reduceFunctionParam);
		GroupByResults r1 = template.group(simpleClassName, groupBy1, e.getClass()); */

        BasicDBList list = (BasicDBList) r1.getRawResults().get("retval");
        return list;
    }

    @Override
    public BasicDBList findWithGroup(E e, boolean isFirstLetterUppercase, String hql, String initalDocumentParam, String reduceFunctionParam, String... keys) {
        return null;
    }

    @Override
    public BasicDBList findWithAggregation4StatLog(long startTime, long endTime, String title, String site) {
/*
        List<BasicDBObject> matchList = new ArrayList<>();

        matchList.add(new BasicDBObject("site", new BasicDBObject("$eq", site)));
        //
        //match
        //title模糊查询
        if (!StringUtils.isEmpty(title)) {
            BasicDBObject dbo = new BasicDBObject();// 新建查询基类对象 dbo
            Pattern pattern = Pattern.compile("^.*" + title + ".*$", Pattern.CASE_INSENSITIVE);
            dbo.put("title", pattern);
            matchList.add(dbo);
        }
        if (startTime >= 0) {
            matchList.add(new BasicDBObject("startTime", new BasicDBObject("$gte", startTime)));
        }
        if (startTime >= 0) {
            matchList.add(new BasicDBObject("startTime", new BasicDBObject("$lte", endTime)));
        }

        BasicDBObject[] array = (BasicDBObject[]) matchList.toArray(new BasicDBObject[0]);

        BasicDBObject cond = new BasicDBObject();
        cond.put("$and", array);
        DBObject match = new BasicDBObject("$match", cond);
        //group
        DBObject groupFields = new BasicDBObject("_id", "$title");
        groupFields.put("count", new BasicDBObject("$sum", 1));
        groupFields.put("sum", new BasicDBObject("$sum", "$visitTime"));
        groupFields.put("avg", new BasicDBObject("$avg", "$visitTime"));
        DBObject group = new BasicDBObject("$group", groupFields);
        //sort
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1));
        //limit
        DBObject limit = new BasicDBObject("$limit", 100000);

        AggregationOutput output = template.getCollection("mLogEnty").aggregate(match, group, sort, limit);
        Iterable<DBObject> list1 = output.results();
        BasicDBList list = new BasicDBList();
        for (DBObject dbObject : list1) {
            dbObject.put("title", dbObject.get("_id"));
            list.add(dbObject);
        }

        return list;*/
        return null;
    }

    /**
     * 根据操作符及查询key,value创建Criteria
     */
    private Criteria buildCriteria(String key, String value, String operator) {
        // 判断属性是否为日期类型,日期类型转特殊构造Criteria
        if (ReflectUtils.fieldIsDate(entityClass, key)) {
            logger.debug("属性:{}为日期", key);
            return buildDateCriteria(key, value, operator);
        } else {
            if (U.safeEquals(operator, HQLParseUtils.FINDS_GT)) {
                return Criteria.where(key).gt(value);
            } else if (U.safeEquals(operator, HQLParseUtils.FINDS_GTE)) {
                return Criteria.where(key).gte(value);
            } else if (U.safeEquals(operator, HQLParseUtils.FINDS_IS)) {
                return Criteria.where(key).is(value);
            } else if (U.safeEquals(operator, HQLParseUtils.FINDS_LT)) {
                return Criteria.where(key).lt(value);
            } else if (U.safeEquals(operator, HQLParseUtils.FINDS_LTE)) {
                return Criteria.where(key).lte(value);
            } else if (U.safeEquals(operator, HQLParseUtils.FINDS_NE)) {
                return Criteria.where(key).ne(value);
            } else if (U.safeEquals(operator, HQLParseUtils.FINDS_REGEX)) {
                return Criteria.where(key).regex(value);
            }
            return new Criteria();
        }
    }

    /**
     * 日期类查询
     */
    private Criteria buildDateCriteria(String key, String value) {
        Date start = DateUtil.parse(value, DateFormatType.YYYY_MM_DD);
        Date end= DateUtil.addDays(start,1);
        return Criteria.where(key).gte(start).lte(end);
    }

    /**
     * 日期类查询
     */
    private Criteria buildDateCriteria(String key, String value, String operator) {
        Date start = DateUtil.parse(value, DateFormatType.YYYY_MM_DD);
        Date end= DateUtil.addDays(start,1);

        if (U.safeEquals(operator, HQLParseUtils.FINDS_LT)) {
            return Criteria.where(key).lt(end);
        } else if (U.safeEquals(operator, HQLParseUtils.FINDS_LTE)) {
            return Criteria.where(key).lte(end);
        } else if (U.safeEquals(operator, HQLParseUtils.FINDS_GT)) {
            return Criteria.where(key).gt(start);
        } else if (U.safeEquals(operator, HQLParseUtils.FINDS_GTE)) {
            return Criteria.where(key).gte(start);
        } else {
            return Criteria.where(key).is(start);
        }
    }

    /**
     * 根据hql创建查询对象,支持分页及排序
     */
    private Query buildQuery(String hql, List<Sort.Order> orders, int pageIndex, int pageSize) {
        /* 查询条件 */
        Criteria criteria = StringUtils.isEmpty(hql) ? new Criteria() : buildCriteria(hql);
        /* 生成查询语句 */
        Query query = ObjectUtils.isEmpty(criteria) ? new Query() : new Query(criteria);
        logger.debug("生成的查询语句:{}", query);
        /* 分组 */
        if (A.isNotEmpty(orders)) {
            logger.debug("排序操作:{}", orders);
            Sort sort = new Sort(orders);
            query = query.with(sort);
        }
        /* 分页 */
        if (pageSize > 0) {
            logger.debug("分页操作,pageIndex:{} pageSize:{}", pageIndex, pageSize);
            query.skip((pageIndex - 1) * pageSize);
            query.limit(pageSize);
        }
        return query;
    }

    /**
     * 生成查询条件语句
     */
    private Criteria buildCriteria(String hql) {
        List<Criteria> criterias = Lists.newArrayList();
        // 解析HQL
        List<String> operators = HQLParseUtils.getOperators(hql); // 解析操作符
        List<String> finds = HQLParseUtils.getFinds(hql); // 解析查询符
//		Map<String, String> map = HQLParseUtils.getMaps(hql);// 解析查询为key-value
        List<MapVo> maplst = HQLParseUtils.getMapVo(hql);
        // 打印日志
        logger.debug("操作符:{}", operators);
        logger.debug("查询符:{}", finds);
        logger.debug("查询key-value:{}", maplst);
        // 遍历key-value生成查询
        int index = 0;
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			if (CollectionUtils.notEmpty(finds)) {
//				criterias.add(buildCriteria(entry.getKey(), entry.getValue(), finds.get(index)));
//			}
//			index++;
//		}
        for (MapVo vo : maplst) {
            if (A.isNotEmpty(finds)) {
                criterias.add(buildCriteria(vo.getKey(), (String) vo.getValue(), finds.get(index)));
            }
            index++;
        }

        if (A.isNotEmpty(criterias)) {
            // 单个查询处理
            if (CollectionUtils.isEmpty(operators)) {
                return criterias.get(0);
            } else {//混合查询
                return mixBuildCriteria(operators, criterias);
            }
        }
        return null;
    }

    /**
     * 混合多条件查询
     */
    private Criteria mixBuildCriteria(List<String> operators, List<Criteria> criterias) {
        // 初始化对象
        Criteria criteria = new Criteria();
        List<Criteria> andList = Lists.newArrayList();
        List<Criteria> orList = Lists.newArrayList();
        //遍历操作符
        for (int i = 0; i < operators.size(); i++) {
            if (i == 0) {// 根据操作符判断首个元素跟第二个元素是AND还是OR操作
                if (U.safeEquals(operators.get(i), HQLParseUtils.OPERATORS_AND)) {
                    andList.add(criterias.get(i));
                } else if (U.safeEquals(operators.get(i), HQLParseUtils.OPERATORS_OR)) {
                    orList.add(criterias.get(i));
                }
            }// 非首个元素处理
            if (U.safeEquals(operators.get(i), HQLParseUtils.OPERATORS_AND)) {
                andList.add(criterias.get(i + 1));
            } else if (U.safeEquals(operators.get(i), HQLParseUtils.OPERATORS_OR)) {
                orList.add(criterias.get(i + 1));
            }
        }
        if (A.isNotEmpty(andList)) {
            Criteria[] cs = new Criteria[andList.size()];
            criteria.andOperator(andList.toArray(cs));
        }

        if (A.isNotEmpty(orList)) {
            Criteria[] cs = new Criteria[orList.size()];
            criteria.orOperator(orList.toArray(cs));
        }

        return criteria;
    }

    /**
     * 根据hql创建查询对象
     */
    private Query buildQuery(String hql) {
        /* 查询条件 */
        Criteria criteria = buildCriteria(hql);
        /* 生成查询语句 */
        Query query = ObjectUtils.isEmpty(criteria) ? new Query() : new Query(criteria);
        logger.debug("生成的查询语句:{}", query);
        return query;
    }

    /**
     * 根据给定的 hql 及 不重复字段  distinctFileld ，返回字段 distinctFileld 不重复的 List 集合
     * hql 如果包含的日期yyyy-MM-dd， 即认为在数据库中是 ISODate 对象，而非字符串
     *
     * @param hql
     * @param collectionName
     * @param distinctFileld
     * @return
     * @throws ParseException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    /**
    public List queryDistinct(String hql, String collectionName, String distinctFileld, Class clz) throws ParseException, InstantiationException, IllegalAccessException {
        SimpleDateFormat df = null;
        df = new SimpleDateFormat("yyyy-MM-dd");
        df.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));

        List<Map> list = new ArrayList<>();
        MongoCollection col = template.getCollection(collectionName);
        DBObject dbo = new BasicDBObject();
        String hqlArgs[] = hql.split("&");
        String splitString[] = {">=", "<=", ">", "<", "!=", "=", "in", "%"};
        String splitStringDesc[] = {"$gte", "$lte", "$gt", "$lt", "$ne", "", "$in"};
        String keys[] = new String[hqlArgs.length];
        for (int i = 0; i < hqlArgs.length; i++) {
            for (int j = 0; j < splitString.length; j++) {
                if (hqlArgs[i].split(splitString[j]).length == 2) {
                    String key = hqlArgs[i].split(splitString[j])[0];
                    keys[i] = key;

                    String value = hqlArgs[i].split(splitString[j])[1];
                    Date d = null;
                    //当前值是否为日期型 yyyy-MM-dd 即认为是日期
                    boolean dateFlag = value.split("-").length == 3;
                    if (dateFlag) {
                        d = df.parse(value);
                    }
                    ;

                    //当匹配的为等号时
                    if (j == 5) {
                        if (dateFlag) {
                            dbo.put(key, d);
                        } else {
                            dbo.put(key, value);
                        }
                    } else if (j == 7) {
                        Pattern pattern = Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
                        dbo.put(key, pattern);
                    } else {
                        if (dateFlag) {
                            dbo.put(key, new BasicDBObject(splitStringDesc[j], d));
                        } else {
                            dbo.put(key, new BasicDBObject(splitStringDesc[j], value));
                        }
                    }

                    //当匹配的为in时
                    if (j == 6) {
                        dbo.removeField(key);
                        String[] values = value.trim().substring(1, value.trim().length() - 1).split(",");
                        for (int k = 0; k < values.length; k++) {
                            values[k] = values[k].trim();
                        }
                        dbo.put(key.trim(), new BasicDBObject(splitStringDesc[j], values));
                        break;
                    }

                    //TODO  put key 最多重复两次
                    for (int k = 0; k < i; k++) {
                        if (key.equals(keys[k])) {
                            dbo.removeField(key);
                            for (int l = 0; l < splitString.length; l++) {
                                if (hqlArgs[k].split(splitString[l]).length == 2) {
                                    if (dateFlag) {
                                        Date d2 = df.parse(hqlArgs[k].split(splitString[l])[1]);
                                        dbo.put(key, new BasicDBObject(splitStringDesc[j], d).append(splitStringDesc[l], d2));
                                    } else {
                                        dbo.put(key, new BasicDBObject(splitStringDesc[j], value).append(splitStringDesc[k], hqlArgs[k].split(splitString[l])[1]));
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        list = col.distinct(distinctFileld, dbo);

        List<DBObject> distinctDocumentList = new ArrayList<DBObject>();
        for (int k = 0; k < list.size(); k++) {
            dbo.put(distinctFileld, list.get(k));
            DBObject cla = col.findOne(dbo);
            distinctDocumentList.add(cla);
        }
        Collections.sort(distinctDocumentList, new SortByLrrqDesc());

		if(!StringUtils.isEmpty(orderByField)){
			if(!CollectionUtils.isEmpty(distinctDocumentList)){
				for (int i = 0; i < distinctDocumentList.size(); i++) {
					Map<Object, Object> tmp0 = (Map<Object, Object>) distinctDocumentList.get(i);
					Date number0 = (Date) tmp0.get(orderByField);

					Map<Object, Object> tmp = null;
					for (int j = i; j < distinctDocumentList.size(); j++) {
						Map<Object, Object> tmp1 = (Map<Object, Object>) distinctDocumentList.get(j);
						Date number1 = (Date) tmp1.get(orderByField);

						if (number0.after(number1)) {
							tmp = tmp0;
							list.set(i, (Map) distinctDocumentList.get(j));
							list.set(j, tmp);
						}
					}
				}
			}
		}


        List returnList = new ArrayList();
        for (int i = 0; i < distinctDocumentList.size(); i++) {
            Object obj = null;
            try {
                obj = clz.newInstance();
                BeanUtils.populate(obj, distinctDocumentList.get(i).toMap());
                returnList.add(obj);
            } catch (InvocationTargetException e) {
                log.error(e.getMessage(), e);
            }
        }

        return returnList;
    }
*/
    class SortByLrrqDesc implements Comparator {

        @SuppressWarnings("unchecked")
        public int compare(Object o1, Object o2) {
            // TODO Auto-generated method stub
            Map<Object, Object> map1 = (Map<Object, Object>) o1;
            Map<Object, Object> map2 = (Map<Object, Object>) o2;
            Date number1 = (Date) map1.get("lrrq");
            Date number2 = (Date) map2.get("lrrq");
            if (number1.after(number2)) {
                return -1;
            }
            return 1;
        }

    }

    @Override
    public void insert(E e, Class clazz) {
        uniqueCheck(e);
        logger.debug("插入对象:{}", clazz);
        template.insert(e, getCollectionNameByClassName(clazz));
    }

    @Override
    public UpdateResult updateOne(String hql, Map<String, Object> updateMap, Class clazz) {
        logger.debug("更新满足条件的第一个记录:{} ", clazz);
        /* 构建查询语句 */
        Query query = buildQuery(hql);
        /* 执行更新 */
        return template.updateFirst(query, buildUpdate(updateMap), getCollectionNameByClassName(clazz));
    }

    @Override
    public DeleteResult remove(String hql, Class clazz) {
        logger.debug("删除满足条件的:{}所有记录 ", clazz);
        /* 创建查询语句 */
        Query query = buildQuery(hql);
        /* 执行删除 */
        return template.remove(query, getCollectionNameByClassName(clazz));
    }

    @Override
    public UpdateResult updateAll(String hql, Map<String, Object> updateMap, Class clazz) {
        logger.debug("更新满足条件的:{}所有记录", entityClass);
        /* 构建查询语句 */
        Query query = buildQuery(hql);
        /* 执行更新 */
        return template.updateMulti(query, buildUpdate(updateMap), getCollectionNameByClassName(clazz));
    }

    /**
     * 获取collection Name，将类名首字母小写改为大写  与java vo/pojo 类名匹配（大小写敏感）
     *
     * @return
     * @Title: getCollectionNameByClassName
     * @Description:
     * @throwsO
     */
    private String getCollectionNameByClassName(Class clazz) {
        String className = clazz.getSimpleName();
        String firstLetter2UpperCase = Character.toString((className.charAt(0))).toUpperCase();
        className = firstLetter2UpperCase + className.substring(1, className.length());
        return className;
    }


}
