package com.acompe.service;

import com.acompe.pojo.User;

public interface UserService {
    User findById(int id);

    User findByUserName(String userName);
}
