package com.sckr.security.service;

import com.sckr.security.po.SysResource;
import com.sckr.security.repository.SysResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 此类用来匹配该请求需要哪些对应的权限
 * @author zhoukebo
 * @date 2018/9/19
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysResourceRepository permissionDao;

    private HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine() {
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute resourceName;
        ConfigAttribute urlString;
        List<SysResource> permissions = permissionDao.findAll();
        for (SysResource permission : permissions) {
            array = new ArrayList<>();
            resourceName = new SecurityConfig(permission.getResourceName());
            urlString = new SecurityConfig(permission.getMethodPath());
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(urlString);
            array.add(resourceName);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getMethodPath(), array);
        }

    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     * 进行判定用户是否有此权限。如果不在权限表中则放行。
     * @param object
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (String s : map.keySet()) {
            resUrl = s;
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}