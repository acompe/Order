package com.acompe.service.impl;

import com.acompe.mapper.ModularMapper;
import com.acompe.pojo.Modular;
import com.acompe.pojo.Structure;
import com.acompe.service.ModularService;
import com.acompe.vo.ModularVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ModularServerImp implements ModularService {

    @Autowired
    private ModularMapper modularMapper;

    @Override
    public List<ModularVo> getAllBlock() {
        List<Modular> all = modularMapper.findAll();
        List<ModularVo> list = new ArrayList<>();
        for (Modular modular:all){
            ModularVo modularVo = new ModularVo(modular);
            List<Structure> structures = JSON.parseArray(modular.getStructure(), Structure.class);
            modularVo.setStructure(structures);
            list.add(modularVo);
        }
        return list;
    }

    @Override
    public Modular addModular(String name, String description) {
        Modular modular = new Modular();
        modular.setName(name);
        modular.setDescription(description);
        modularMapper.addModular(modular);
        return modular;
    }

    @Override
    public boolean delModular(int id) {
        if (modularMapper.delModular(id) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateStructure(int id, String structure) {
        if (modularMapper.updateStructure(id,structure) == 1){
            return true;
        }else {
            return false;
        }
    }
}
