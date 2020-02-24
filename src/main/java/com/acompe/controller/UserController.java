package com.acompe.controller;

import com.acompe.pojo.User;
import com.acompe.service.GithubService;
import com.acompe.service.UserService;
import com.acompe.utils.JwtUtil;
import com.acompe.vo.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private GithubService githubService;
    @Autowired
    private UserService userService;


    @ApiOperation(value = "管理员登录", notes = "管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",example = "hehe"),
            @ApiImplicitParam(name = "password",value = "密码",example = "haha")
    })

    @PostMapping("login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        Message message = new Message();
        User byUserName = userService.findByUserName(username);
        if (byUserName!=null){
            if (byUserName.getPassword().equals(password)){
                message.setTrue(jwtUtil.generateToken(byUserName));
            }else {
                message.setFalse();
            }
        }else {
            message.setFalse();
        }
        return message.toString();
    }

    @PostMapping("info")
    public String Info(@ApiIgnore Authentication authentication){
        Message message = new Message();
        if (authentication.getPrincipal() instanceof User){
            User user = (User) authentication.getPrincipal();
            message.setTrue(githubService.findByUserId(user.getId()));
            return message.toString();
        }else {
            message.setFalse();
            return message.toString();
        }
    }
}
