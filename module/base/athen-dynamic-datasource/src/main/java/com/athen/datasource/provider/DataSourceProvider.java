package com.athen.datasource.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 多数据源加载接口，默认的实现为从yml信息中加载所有数据源
 * 你可以自己实现从其他地方加载所有数据源
 * User: chenying
 * Date: 2019-07-11
 * Time: 17:17
 * since: 1.0.0
 */
public interface DataSourceProvider {

    Map<String, DataSource> loadDataSource();
}
