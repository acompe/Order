package com.acompe.service.impl;

import com.acompe.mapper.GithubMapper;
import com.acompe.mapper.UserMapper;
import com.acompe.pojo.GitHub;
import com.acompe.pojo.User;
import com.acompe.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubServiceImpl implements GithubService {

    @Autowired
    private GithubMapper githubMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(GitHub gitHub) {
        GitHub gitHubByGitHubId = githubMapper.findByGitHubId(gitHub.getGitHubId());
        if (gitHubByGitHubId==null){
            User user= new User();
            userMapper.addUser(user);

            gitHub.setUserId(user.getId());
            githubMapper.addGitHub(gitHub);
            return user;
        }else {
            User userById = userMapper.findById(gitHubByGitHubId.getUserId());
            return userById;
        }
    }

    @Override
    public GitHub findByUserId(int userId) {
        return githubMapper.findByUserId(userId);
    }
}
