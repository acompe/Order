package com.acompe.vo;

import com.acompe.pojo.Modular;
import com.acompe.pojo.Structure;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModularVo {
    private int id;
    private String name;
    private String description;
    private List<Structure> structure;

    public ModularVo(Modular modular){
        this.id = modular.getId();
        this.name = modular.getName();
        this.description = modular.getDescription();
        this.structure = new ArrayList<>();
    }
}
