package com.sckr.security.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类，这里要成为spring security使用的实体类必须要实现UserDetails接口
 * @author zhoukebo
 * @date 2018/9/4
 */
@Entity
@Data
public class SysUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    /***和角色是多对多的关系*/
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;

    @Transient
    private  Collection<? extends GrantedAuthority> grantedAuthority;

    /***
     * 正常情况下，角色和权限是两回事，
     * 所以我们还需要重写getAuthorities方法，将用户的角色和权限关联起来
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
