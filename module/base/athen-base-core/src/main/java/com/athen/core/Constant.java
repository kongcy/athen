package com.athen.core;

/**
 * Created by foresee on 2019-01-27.
 * 常量类
 */
public class Constant {

    public final static String DUBBO_GROUP=Dubbo.V1.getGroup();

    public final static String DUBBO_VERSION="1.0.0";

    /**
     * dubbo版本控制，以后升级接口直接修改dubbo服务端，版本号。
     * *
     */
    public static enum Dubbo {
        V1("athenSystem", "1.0.0"),
        V2("athenSystem", "2.0.0");
        private String group;  //dubbo云上电厅的服务组
        private String version; //服务版本号，该版本有客户端传入，dubbo服务升级，通过客户端版本号可以控制调用不同的版本服务，解决版本兼容问题

        Dubbo(String group, String version) {
            this.group = group;
            this.version = version;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

    }

}
