package com.acompe.config;

import com.acompe.filter.TokenOncePerRequestFilter;
import com.acompe.handle.GithubHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private GithubHandle githubHandle;
    @Autowired
    private TokenOncePerRequestFilter tokenOncePerRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有请求均通过
        http.authorizeRequests().anyRequest().permitAll();
        //关闭相关功能
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        //开启OAuth登录功能,并注册成功
        http.oauth2Login().successHandler(githubHandle);
        //设置拦截器
        http.addFilterBefore(tokenOncePerRequestFilter,UsernamePasswordAuthenticationFilter.class);
    }


}
