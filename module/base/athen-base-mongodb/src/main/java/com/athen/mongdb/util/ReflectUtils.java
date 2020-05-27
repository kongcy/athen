package com.athen.mongdb.util;

import com.athen.core.util.U;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Date;
import java.util.List;

/**
 * 反射工具包
 * User: chenying
 * Date: 2019-07-19
 * Time: 16:40
 * since: 1.0.0
 */
@Slf4j
public abstract class ReflectUtils {
    /**
     * 调用Getter方法.
     */
    public static Object invokeGetterMethod(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, new Class[]{},
                new Object[]{});
    }

    /**
     * 调用Setter方法.使用value的Class来查找Setter方法.
     */
    public static void invokeSetterMethod(Object obj, String propertyName,
                                          Object value) {
        invokeSetterMethod(obj, propertyName, value, null);
    }

    /**
     * 调用Setter方法.
     *
     * @param propertyType 用于查找Setter方法,为空时使用value的Class替代.
     */
    public static void invokeSetterMethod(Object obj, String propertyName,
                                          Object value, Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class[]{type},
                new Object[]{value});
    }

    /**
     * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
     */
    public static Object getFieldValue(final Object obj, final String fieldName) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field ["
                    + fieldName + "] on target [" + obj + "]");
        }

        Object result = null;
        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            log.warn(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
     */
    public static void setFieldValue(final Object obj, final String fieldName,
                                     final Object value) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field ["
                    + fieldName + "] on target [" + obj + "]");
        }

        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            // logger.error("不可能抛出的异常:{}", e.getMessage());
            log.warn(e.getMessage(), e);
//			log.error(e.getMessage(), e);
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
     * <p/>
     * 如向上转型到Object仍无法找到, 返回null.
     */
    public static Field getAccessibleField(final Object obj,
                                           final String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {// NOSONAR
                // Field不在当前类定义,继续向上转型
                log.warn(e.getMessage(), e);
//				log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符. 用于一次性调用的情况.
     */
    public static Object invokeMethod(final Object obj,
                                      final String methodName, final Class<?>[] parameterTypes,
                                      final Object[] args) {
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method ["
                    + methodName + "] on target [" + obj + "]");
        }

        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
     * <p/>
     * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object...
     * args)
     */
    public static Method getAccessibleMethod(final Object obj,
                                             final String methodName, final Class<?>... parameterTypes) {

        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                Method method = superClass.getDeclaredMethod(methodName,
                        parameterTypes);

                method.setAccessible(true);

                return method;

            } catch (NoSuchMethodException e) {// NOSONAR
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     * <p/>
     * 如public UserDao extends HibernateDao<User,Long>
     */
    @SuppressWarnings("rawtypes")
    public static Class getSuperClassGenricType(final Class clazz,
                                                final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(
            Exception e) {
        if (e instanceof IllegalAccessException
                || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException("Reflection Exception.", e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException("Reflection Exception.",
                    ((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }

    /**
     * 判断类是否包含指定属性,不存在时返回为空
     */
//	public static Field hasField(Class<?> clazz, String fieldName) {
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			if (StringUtils.safeEquals(field.getName(), fieldName)) {
//				return field;
//			}
//		}
//		return null;
//	}

    /**
     * 判断类是否包含指定属性,不存在时返回为空
     * 集合:
     * testVo:{parent:{child:1} }
     * Vo类:
     * class TestVo{
     * Parent parent;
     * }
     * <p>
     * class Parent{
     * Child child;
     * }
     * <p>
     * hql查询语句:
     * parent.child=1
     */
    public static Field hasField(Class<?> clazz, String fieldName) {
        Field result = null;
        //分隔parent.child格式的子节点查询条件
        String[] fieldNames = fieldName.split("\\.");
        //匹配查询节点与Vo类的层级,如查询testVo集合中的parent.child匹配TestVo类下的parent属性的child属性
        for (String field_name : fieldNames) {
            try {
                //匹配到parent节点与TestVo下的parent属性，如果没有匹配到，则抛出异常被catch捕捉
                result = clazz.getDeclaredField(field_name);
                //如果能匹配到，则获取Parent类的属性，准备匹配child属性
                clazz = result.getType();
            } catch (NoSuchFieldException e) {
                log.warn(e.getMessage(), e);
//				log.error(e.getMessage(), e);
                return null;
            }
        }
        return result;
    }

    /**
     * 通过反射获取指定类下的属性是否包含特定的annotation注解,存在则返回该属性名 不存在则返回为空
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<String> containsAnnotation(Class<?> clazz, Class annotationClass) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> properties = Lists.newArrayList();
        for (Field field : fields) {
            try {
                Annotation result = field.getAnnotation(annotationClass);
                if (U.isBlank(result)) {
                    continue;
                }
                properties.add(field.getName());
            } catch (NullPointerException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return properties;
    }

    /**
     * 判断类是否包含指定属性并且为指定类型
     */
    public static boolean isFieldType(Class<?> clazz, String fieldName,
                                      Class<?> type) {
        Field field = hasField(clazz, fieldName);
        if (U.isBlank(field)) {
            return false;
        }
        return field.getType().equals(type);
    }


    /**
     * 判断类的指定属性是否为Date类型
     */
    public static boolean fieldIsDate(Class<?> clazz, String fieldName) {

        return isFieldType(clazz, fieldName, Date.class);
    }

    /**
     * 获取字段的数据类型
     *
     * @param clazz     根节点类
     * @param fieldName 属性名
     * @return 属性的数据类型, 如果没有定义该字段则返回null
     */
    public static Class<?> getFieldType(Class<?> clazz, String fieldName) {
        Field field = hasField(clazz, fieldName);
        if (field == null) {
            return null;
        }
        return field.getType();
    }

}
