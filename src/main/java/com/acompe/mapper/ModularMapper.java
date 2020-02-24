package com.acompe.mapper;

import com.acompe.pojo.Modular;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ModularMapper {
    /**
     * 去数据库查询所有模块
     * @return
     */
    List<Modular> findAll();

    /**
     * 创建模块
     * @param modular
     */
    void addModular(Modular modular);

    /**
     * 删除相关内容
     * @param id
     * @return
     */
    int delModular(@Param("id") int id);

    /**
     * 更新结构
     * @param id
     * @param structure
     * @return
     */
    int updateStructure(@Param("id") int id, @Param("structure") String structure);
}
