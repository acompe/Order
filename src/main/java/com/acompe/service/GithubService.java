package com.acompe.service;

import com.acompe.pojo.GitHub;
import com.acompe.pojo.User;

public interface GithubService {
    /**
     * 根据github登录
     * @param gitHub
     * @return
     */
    User login(GitHub gitHub);

    /**
     * 根据userid查找用户信息
     * @param userId
     * @return
     */
    GitHub findByUserId(int userId);
}
