package com.sckr.security.repository;

import com.sckr.security.po.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author zhoukebo
 * @date 2018/9/4
 */
@Component
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {}
