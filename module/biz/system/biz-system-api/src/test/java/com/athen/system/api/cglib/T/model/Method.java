package com.athen.system.api.cglib.T.model;

import lombok.Data;

import java.io.Serializable;

/**
 * User: chenying
 * Date: 2019-07-16
 * Time: 18:15
 * since: 1.0.0
 */
@Data
public class Method implements Serializable {
    private String name;
    private String params;
    private String paramType;
}
