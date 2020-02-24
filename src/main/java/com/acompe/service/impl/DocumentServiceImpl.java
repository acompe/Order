package com.acompe.service.impl;

import com.acompe.mapper.DocumentMapper;
import com.acompe.pojo.Document;
import com.acompe.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public boolean updateDocument(Document document) {
        Document isSave = documentMapper.findByParentId(document.getParentId());
        if (isSave==null){
            if (documentMapper.addDocument(document)==1){
                return true;
            }else {
                return false;
            }
        }else {
            if (documentMapper.updateDocument(document) == 1){
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public Document getDocument(int parentId) {
        return documentMapper.findByParentId(parentId);
    }
}
