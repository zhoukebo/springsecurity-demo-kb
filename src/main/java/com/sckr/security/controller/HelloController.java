package com.sckr.security.controller;

import com.sckr.security.common.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoukebo
 * @date 2018/9/4
 */
@RestController
public class HelloController {

    @GetMapping("/user")
    public Result login(String name) {
        System.out.println("欢迎使用springsecurity");
        return new Result("登录页", "欢迎"+name+"进入security的世界", "拥有ADMIN权限访问");
    }

    @RequestMapping("/hello/{name}")
    //使用此注解会调用自定义权限验证逻辑，此处的参数会作为第二个和第三个参数传入
    @PreAuthorize("hasPermission('/hello/**','r')")
    public Result hello(@PathVariable("name") String name) {
        return new Result("欢迎页", "欢迎"+name+"进入security的世界", "拥有ADMIN权限访问");
    }

    @RequestMapping("/add")
    public Result add() {
        return new Result("添加页", "添加内容", "额外信息，只对管理员显示");
    }

    @RequestMapping("/home")
    public Result home() {
        return new Result("主页", "主页", "额外信息，只对管理员显示");
    }
}
