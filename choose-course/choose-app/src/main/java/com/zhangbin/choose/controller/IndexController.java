package com.zhangbin.choose.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(value = "IndexController")
@Controller
public class IndexController {

    /**
     * 跳转到login.html页面
     *
     * @return
     */
    @ApiOperation(value = "跳转到登陆主页", notes = "跳转到登陆主页方法详情", tags = {"IndexController"})
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * 跳转到index.html页面
     *
     * @return
     */
    @ApiOperation(value = "跳转到index首页", notes = "跳转到index首页方法详情", tags = {"IndexController"})
    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

}
