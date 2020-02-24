package com.acompe.controller;


import com.acompe.pojo.Document;
import com.acompe.pojo.Modular;
import com.acompe.pojo.User;
import com.acompe.service.DocumentService;
import com.acompe.service.ModularService;
import com.acompe.vo.Message;
import com.acompe.vo.ModularVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@Api(tags = "模块接口")
@RestController
@RequestMapping("document")
public class ModelController {
    @Autowired
    private ModularService modularService;
    @Autowired
    private DocumentService documentService;

    @ApiOperation(value = "获取文档", notes = "获取所有相关文档")
    @PostMapping("modular")
    public String block(){
        Message message = new Message();
        List<ModularVo> allBlock = modularService.getAllBlock();
        message.setTrue(allBlock);
        return message.toString();
    }

    @ApiOperation(value = "添加文档", notes = "根据传入内容添加文档")
    @PostMapping("addModular")
    public String addModular(@ApiIgnore Authentication authentication, @RequestParam("name") String name,@RequestParam("description") String description){
        Message message = new Message();
        if (authentication==null){
            message.setFalse();
            return message.toString();
        }
        User user;
        if (authentication.getPrincipal() instanceof User){
            Modular modular = modularService.addModular(name, description);
            message.setTrue(modular);
        }else {
            message.setFalse("请先登录");
        }
        return message.toString();
    }

    @ApiOperation(value = "删除文档", notes = "根据传入的ID删除文档")
    @PostMapping("delModular")
    public String delStructure(@ApiIgnore Authentication authentication, @RequestParam("id") int id){
        Message message = new Message();
        if (authentication==null){
            message.setFalse("请先登录");
            return message.toString();
        }
        User user;
        if (authentication.getPrincipal() instanceof User){
            if (modularService.delModular(id)){
                message.setTrue();
            }else {
                message.setFalse("找不到该模块");
            }
        }
        return message.toString();

    }

    @ApiOperation(value = "更新结构", notes = "根据传入的ID,结构进行更新")
    @PostMapping("updateStructure")
    public String updateModular(@ApiIgnore Authentication authentication, @RequestParam("id") int id,@RequestParam("structure") String structure){
        Message message = new Message();
        if (authentication==null){
            message.setFalse("请先登录");
            return message.toString();
        }
        User user;
        if (authentication.getPrincipal() instanceof User){
            if (modularService.updateStructure(id,structure)){
                message.setTrue();
            }else {
                message.setFalse("找不到该模块");
            }
        }
        return message.toString();
    }

    @ApiOperation(value = "文档内容", notes = "根据传入的ID,获取文档内容")
    @PostMapping("content")
    public String content(@RequestParam("parentId") int parentId){
        Message message = new Message();
        Document document = documentService.getDocument(parentId);
        message.setTrue(document);
        return message.toString();
    }

    @ApiOperation(value = "保存文档", notes = "根据传入的ID,文档进行保存")
    @PostMapping("updateDocument")
    public String updateDocument(@ApiIgnore Authentication authentication, @RequestParam("parentId") int parentId,@RequestParam("content") String content){
        Message message = new Message();
        if (authentication==null){
            message.setFalse("请先登录");
            return message.toString();
        }
        User user;
        if (authentication.getPrincipal() instanceof User){
            Document document = new Document();
            document.setParentId(parentId);
            document.setContent(content);
            if (documentService.updateDocument(document)){
                message.setTrue();
            }else {
                message.setFalse("找不到该模块");
            }
        }
        return message.toString();
    }
}
