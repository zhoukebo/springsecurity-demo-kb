package com.sckr.security.repository;

import com.sckr.security.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author zhoukebo
 * @date 2018/9/4
 */
@Component
public interface SysUserRepository  extends JpaRepository<SysUser,Long> {

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 系统用户
     */
    SysUser findByUsername(String username);

}
