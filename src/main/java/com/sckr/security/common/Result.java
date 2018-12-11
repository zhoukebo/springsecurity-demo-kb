package com.sckr.security.common;

import lombok.Data;

/**
 * 客户端返回的结果
 * @author zhoukebo
 * @date 2018/9/4
 */
@Data
public class Result {

    private String title;

    private String content;

    private String extraInfo;


    public Result(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }
}
