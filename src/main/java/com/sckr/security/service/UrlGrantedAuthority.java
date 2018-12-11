package com.sckr.security.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhoukebo
 * @date 2018/9/19
 */
@Data
public class UrlGrantedAuthority implements GrantedAuthority {

    private String permissionUrl;
    private String method;

    public UrlGrantedAuthority(String permissionUrl, String method) {
        this.permissionUrl = permissionUrl;
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return this.method;
    }
}
