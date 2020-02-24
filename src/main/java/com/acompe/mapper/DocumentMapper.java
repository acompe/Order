package com.acompe.mapper;

import com.acompe.pojo.Document;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DocumentMapper {

    /**
     * 根据id查找文章
     * @param parentId
     * @return
     */
    Document findByParentId(@Param("parentId") int parentId);

    /**
     * 插入新的文档
     * @param document
     * @return
     */
    int addDocument(Document document);

    /**
     * 更新文档
     * @param document
     * @return
     */
    int updateDocument(Document document);
}
