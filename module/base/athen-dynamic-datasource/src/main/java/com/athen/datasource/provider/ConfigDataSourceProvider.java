package com.athen.datasource.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 从配置中心获取数据源配置
 * User: chenying
 * Date: 2019-07-12
 * Time: 17:32
 * since: 1.0.0
 */
public class ConfigDataSourceProvider implements DataSourceProvider {
    @Override
    public Map<String, DataSource> loadDataSource() {
        return null;
    }
}
