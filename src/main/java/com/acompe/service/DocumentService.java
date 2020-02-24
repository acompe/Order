package com.acompe.service;

import com.acompe.pojo.Document;

public interface DocumentService {
    /**
     * 有更新文档内容
     * 无则创建文档内容
     * @param document
     * @return
     */
    boolean updateDocument(Document document);

    /**
     * 根据parentId获取文章
     * @param parentId
     * @return
     */
    Document getDocument(int parentId);
}
