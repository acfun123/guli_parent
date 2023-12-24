package com.cc.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.Result;
import com.cc.eduservice.entity.EduTeacher;
import com.cc.eduservice.entity.vo.TeacherQueryVO;
import com.cc.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author cc
 * @since 2023-05-26
 */
@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class EduTeacherController {

    @Resource
    EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有讲师")
    public Result findAll() {
        Map<String, Object> data = new HashMap<>();
        List<EduTeacher> list = eduTeacherService.list(null);
        data.put("size", list.size());
        data.put("list", list);
        return Result.ok().data(data);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public Result removeTeacherById(@PathVariable @ApiParam(name = "id", value = "讲师ID") String id) {
        Map<String, Object> data = new HashMap<>();
        eduTeacherService.removeById(id);
        data.put("status", true);
        return Result.ok().data(data);
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation("分页查询讲师")
    public Result pageTeacher(@PathVariable Integer current, @PathVariable Integer limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        IPage<EduTeacher> page = eduTeacherService.page(pageTeacher, new TeacherQueryVO());
        Map<String, Object> data = new HashMap<>();
        data.put("current", current);
        data.put("limit", limit);
        data.put("size", page.getTotal());
        data.put("list", page.getRecords());
        return Result.ok().data(data);
    }

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation("条件分页查询讲师")
    public Result pageTeacherCondition(@PathVariable Integer current, @PathVariable Integer limit,
                                       @RequestBody TeacherQueryVO queryVO) {
        //创建分页对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        IPage<EduTeacher> page = eduTeacherService.page(pageTeacher, queryVO);
        Map<String, Object> data = new HashMap<>();
        data.put("size", page.getTotal());
        data.put("current", current);
        data.put("limit", limit);
        data.put("list", page.getRecords());
        return Result.ok().data(data);
    }

    @PostMapping("addTeacher")
    @ApiOperation("添加讲师")
    public Result addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = eduTeacherService.save(teacher);
        return save ? Result.ok() : Result.error();
    }

    @GetMapping("getTeacher/{id}")
    @ApiOperation("根据id获取讲师信息")
    public Result getTeacher(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher", teacher);
    }

    @PostMapping("updateTeacher")
    @ApiOperation("根据id更新讲师")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        return flag ? Result.ok() : Result.error();
    }
}

