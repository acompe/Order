package com.acompe.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "测试接口")
@Controller

public class IndexController {

    @ApiOperation(value = "测试页面",notes = "测试接口请忽视")
    @ApiImplicitParams({
    })

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

}