package com.cc.eduservice.mapper;

import com.cc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.eduservice.entity.vo.CourseInfoVO;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author cc
 * @since 2023-06-27
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    List<CourseInfoVO> findAll();
}
