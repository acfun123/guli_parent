package com.cc.eduservice.controller;


import com.cc.Result;
import com.cc.eduservice.entity.EduSubject;
import com.cc.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author cc
 * @since 2023-06-24
 */
@RestController
@RequestMapping("/subject")
@CrossOrigin
public class EduSubjectController {

    @Resource
    EduSubjectService eduSubjectService;

    @PostMapping("readAndAdd")
    @ApiOperation(value = "添加科目")
    public Result readSubject(MultipartFile file) throws IOException {
        eduSubjectService.readAndAdd(file);
        return Result.ok();
    }

    @PostMapping("findTree")
    @ApiOperation(value = "查询所有科目")
    public Result findTree(){
        List<EduSubject> list = eduSubjectService.findTree();
        return Result.ok().data("list", list);
    }

    @GetMapping("findSubjectByParentId/{parentId}")
    @ApiOperation(value = "根据parentId查询科目")
    public Result findSubjectByParentId(@PathVariable String parentId){
        List<EduSubject> list = eduSubjectService.findSubjectByParentId(parentId);
        return Result.ok().data("list", list);
    }
}

