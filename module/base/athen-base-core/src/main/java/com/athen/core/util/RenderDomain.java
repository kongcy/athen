package com.athen.core.util;

import lombok.Data;

@Data
public class RenderDomain {

    /** pc 端 */
    private String index;

    /** 接口调用的域名, 走 https */
    private String api;

    /** html 5 域名, 主要基于 wechat */
    //private String mobile;

    /** 资源文件域名, css png js 等 */
    private String still;

    /** 用户上传的图片、文件(按目录区分)域名 */
    private String upload;

    /** 后台系统的域名 */
    private String manager;
}
