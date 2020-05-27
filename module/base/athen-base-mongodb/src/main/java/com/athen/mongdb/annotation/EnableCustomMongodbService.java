package com.athen.mongdb.annotation;

import com.athen.mongdb.config.MongodbServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * User: chenying
 * Date: 2019-08-09
 * Time: 15:08
 * since: 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({MongodbServiceConfiguration.class})
@Configuration
public @interface EnableCustomMongodbService {
}
