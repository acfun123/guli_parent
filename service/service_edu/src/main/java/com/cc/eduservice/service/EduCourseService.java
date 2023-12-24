package com.cc.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.eduservice.entity.EduCourse;
import com.cc.eduservice.entity.vo.CourseInfoVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cc
 * @since 2023-06-27
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfoVO(CourseInfoVO courseInfoVO);

    boolean deleteById(String id);

    List<CourseInfoVO> findAll();

    CourseInfoVO getCourseInfoById(String id);

    boolean updateCourseInfoVOById(CourseInfoVO entity);
}
