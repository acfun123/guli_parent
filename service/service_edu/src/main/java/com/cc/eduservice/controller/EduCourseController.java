package com.cc.eduservice.controller;


import com.cc.Result;
import com.cc.eduservice.entity.vo.CourseInfoVO;
import com.cc.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cc
 * @since 2023-06-27
 */
@RestController
@RequestMapping("/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    @PostMapping("save")
    public Result save(@RequestBody CourseInfoVO courseInfoVO){
        return Result.ok().data("id", eduCourseService.saveCourseInfoVO(courseInfoVO));
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id){
        boolean flag = eduCourseService.deleteById(id);
        return flag ? Result.ok() : Result.error();
    }

    @GetMapping("/getCourseInfoById/{id}")
    public Result getCourseInfoById(@PathVariable String id) {
        return Result.ok().data("courseInfo", eduCourseService.getCourseInfoById(id));
    }

    @PostMapping
    @GetMapping("findAll")
    public Result findAll(){
        List<CourseInfoVO> courseInfoVOS = eduCourseService.findAll();
        return Result.ok().data("list", courseInfoVOS);
    }

    @PostMapping("updateCourse")
    @ApiOperation("根据id更新课程")
    public Result updateCourse(@RequestBody CourseInfoVO courseInfoVO) {
        boolean flag = eduCourseService.updateCourseInfoVOById(courseInfoVO);
        return flag ? Result.ok() : Result.error();
    }

}

