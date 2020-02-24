package com.acompe.mapper;

import com.acompe.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findById(@Param("id") int id);

    /**
     * 根据UserName查找用户
     * @param userName
     * @return
     */
    User findByUserName(@Param("userName") String userName);
}
