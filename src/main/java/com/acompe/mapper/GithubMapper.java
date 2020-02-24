package com.acompe.mapper;

import com.acompe.pojo.GitHub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GithubMapper {
    /**
     * 根据GitHUbId查询
     * @param gitHubId
     * @return
     */
    GitHub findByGitHubId(@Param("gitHubId") String gitHubId);

    /**
     * 添加GitHub用户
     * @param gitHub
     * @return
     */
    int addGitHub(GitHub gitHub);

    /**
     * 根据id查找gitHub
     * @param userId
     * @return
     */
    GitHub findByUserId(@Param("userId") int userId);
}
