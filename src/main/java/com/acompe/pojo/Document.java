package com.acompe.pojo;

import lombok.Data;

@Data
public class Document {
    private int id;
    private int parentId;
    private String content;
}
