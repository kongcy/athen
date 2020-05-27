package com.athen.dubbo.listener;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.InvokerListenerAdapter;
import com.athen.dubbo.model.ClassIdBean;
import com.athen.dubbo.model.Constants;

import java.util.Set;

/**
 * User: chenying
 * Date: 2019-07-30
 * Time: 16:54
 * since: 1.0.0
 */
public class ConsumerSubscribeListener extends InvokerListenerAdapter {
    /**
     * subscribe interfaces
     */
    public static final Set<ClassIdBean> SUBSCRIBEDINTERFACES_SET = new ConcurrentHashSet<ClassIdBean>();

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        Class<?> interfaceClass = invoker.getInterface();
        URL url = invoker.getUrl();
        String group = url.getParameter(Constants.GROUP);
        String version = url.getParameter(Constants.VERSION);
        ClassIdBean classIdBean = new ClassIdBean(interfaceClass, group, version);
        SUBSCRIBEDINTERFACES_SET.add(classIdBean);
    }

    @Override
    public void destroyed(Invoker<?> invoker) {
        Class<?> interfaceClass = invoker.getInterface();
        URL url = invoker.getUrl();
        String group = url.getParameter(Constants.GROUP);
        String version = url.getParameter(Constants.VERSION);
        ClassIdBean classIdBean = new ClassIdBean(interfaceClass, group, version);
        SUBSCRIBEDINTERFACES_SET.remove(classIdBean);
    }
}
