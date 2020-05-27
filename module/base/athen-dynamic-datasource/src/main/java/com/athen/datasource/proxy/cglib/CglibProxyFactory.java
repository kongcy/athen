package com.athen.datasource.proxy.cglib;

import com.athen.core.util.A;
import com.athen.core.util.U;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: chenying
 * Date: 2019-08-02
 * Time: 16:50
 * since: 1.0.0
 */
@Slf4j
public class CglibProxyFactory {

    private static final String SET_NON_NULL_PARAMETER = "setNonNullParameter";
    private static final String GET_NULL_ABLE_RESULT = "getNullableResult";
    private Map<String, Object> cache = new HashMap<>();

    /**
     * 获取代理对象
     **/
    public static Object proxy(Class<?> clazz, Callback callback) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(callback);
        return enhancer.create();
    }

    public static class ProxyMethodInterceptor implements MethodInterceptor {

        private Class clazz;

        public ProxyMethodInterceptor(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (clazz == null) {
                throw new RuntimeException("请先传入数据类型 clazz: {}" + clazz);
            }
            log.info("method: "+method.getName()+" 方法参数: "+A.toStr(args));
            int state = method.getModifiers();
            Object ret;
            if (state == 1024) {
                ret = converObj(method, args);
            } else {
                ret = proxy.invokeSuper(obj, args);
            }
            return ret;
        }

        private Object converObj(Method method, Object[] args) throws SQLException {
            if (SET_NON_NULL_PARAMETER.equals(method.getName())) {
                if (A.isNotEmpty(args) && args.length == 4) {
                    PreparedStatement ps = (PreparedStatement) args[0];
                    int i = (int) args[1];
                    Enum parameter = (Enum) args[2];
                    JdbcType jdbcType = U.isBlank(args[3]) ? null : (JdbcType) args[3];
                    if (jdbcType == null || jdbcType == JdbcType.VARCHAR) {
                        ps.setString(i, parameter.name());
                    } else {
                        Object obj = U.getMethod(parameter, "getCode");
                        ps.setInt(i, obj == null ? 0 : Integer.parseInt(obj.toString()));
                    }
                }
            } else if (GET_NULL_ABLE_RESULT.equals(method.getName())) {
                if (A.isNotEmpty(args) && args.length == 2) {
                    Object args0 = args[0];
                    Object args1 = args[1];
                    if (args0 != null && args0.getClass().isAssignableFrom(ResultSet.class)) {
                        ResultSet rs = (ResultSet) args0;
                        if (args1 != null) {
                            if (args1.getClass().isAssignableFrom(String.class)) {
                                return U.toEnum(clazz, rs.getObject((String) args1));
                            } else {
                                return U.toEnum(clazz, rs.getObject((int) args1));
                            }
                        }

                    } else {
                        CallableStatement cs = (CallableStatement) args0;
                        return U.toEnum(clazz, cs.getObject((String) args1));
                    }
                }
            }
            return null;
        }
    }
}
