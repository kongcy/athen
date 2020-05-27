package com.athen.datasource.processor.iterator;

import com.athen.datasource.processor.DsProcessor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.List;

/**
 * User: chenying
 * Date: 2019-07-10
 * Time: 14:59
 * since: 1.0.0
 */
public class ProcessBuilder extends AbstractProcessorBuilder {

    public ProcessBuilder(List<DsProcessor> processors) {
        super(processors);
    }

    @Override
    public String determineDatasource(MethodInvocation invocation, String key) {
        while (this.hasNext()) {
            DsProcessor processor = this.next();
            if (processor != null) {
                String dataSource= processor.determineDatasource(invocation, key);
                if(dataSource!=null){
                    return dataSource;
                }
            }
        }
        return null;
    }
}
