package com.acompe.service;

import com.acompe.pojo.Modular;
import com.acompe.vo.ModularVo;

import java.util.List;

public interface ModularService {
    /**
     * 获取所有模块
     * @return
     */
    public List<ModularVo> getAllBlock();

    /**
     * 添加模块
     * @param name
     * @param description
     * @return
     */
    Modular addModular(String name, String description);

    /**
     * 删除模块
     * @param id
     * @return
     */
    boolean delModular(int id);

    /**
     * 更新Structure模块
     * @param id
     * @param structure
     * @return
     */
    boolean updateStructure(int id, String structure);
}
