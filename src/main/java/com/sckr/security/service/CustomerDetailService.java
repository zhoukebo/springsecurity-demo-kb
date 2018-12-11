package com.sckr.security.service;

import com.sckr.security.po.SysResource;
import com.sckr.security.po.SysRole;
import com.sckr.security.po.SysUser;
import com.sckr.security.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要自定义UserDetailsService实现spring security的UserDetailsService接口
 * @author zhoukebo
 * @date 2018/9/4
 */
@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SysRole> roles = user.getRoles();

        //将所有的角色对应的资源权限全部放入user对应的grantedAuthority集合中
        for (SysRole role : roles) {
            List<SysResource> resources = role.getResources();
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysResource resource : resources) {
                if (resource != null && resource.getResourceName()!=null) {
                    GrantedAuthority grantedAuthority = new UrlGrantedAuthority(resource.getMethodPath(),resource.getResourceName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setGrantedAuthority(grantedAuthorities);
        }

        System.out.println("s:" + username);
        return user;
    }
}
