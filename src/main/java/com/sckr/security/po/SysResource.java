package com.sckr.security.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author zhoukebo
 * @date 2018/9/4
 */
@Entity
@Data
public class SysResource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    /**控制的url*/
    private String resourceString;
    /**资源ID*/
    private String resourceId;
    /**备注*/
    private String remark;
    /**资源名称*/
    private String resourceName;
    /**资源对应的方法名*/
    private String methodName;
    /**资源所对应的包路径*/
    private String methodPath;

}
