package com.cc.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.eduservice.entity.EduCourse;
import com.cc.eduservice.entity.EduCourseDescription;
import com.cc.eduservice.entity.vo.CourseInfoVO;
import com.cc.eduservice.mapper.EduCourseDescriptionMapper;
import com.cc.eduservice.mapper.EduCourseMapper;
import com.cc.eduservice.service.EduCourseService;
import com.ccbase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cc
 * @since 2023-06-27
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    @Resource
    EduCourseDescriptionMapper eduCourseDescriptionMapper;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public String saveCourseInfoVO(CourseInfoVO courseInfoVO) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO, eduCourse);
        boolean saveFlag = save(eduCourse);

        if (!saveFlag) {
            throw new GuliException(20001, "保存课程失败!");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseInfoVO.getDescription());
        int insert = eduCourseDescriptionMapper.insert(eduCourseDescription);

        if (insert == 0) {
            throw new GuliException(20001, "保存课程简介失败！");
        }
        return eduCourse.getId();
    }

    @Override
    public boolean deleteById(String id) {
        boolean flag = removeById(id);
        if (!flag) {
            throw new GuliException(20001, "删除课程失败！");
        }

        int i = eduCourseDescriptionMapper.deleteById(id);
        if (i == 0) {
            throw new GuliException(200001, "删除课程简介失败！");
        }
        return true;
    }

    @Override
    public List<CourseInfoVO> findAll() {
        return baseMapper.findAll();
    }

    @Override
    public CourseInfoVO getCourseInfoById(String id) {
        EduCourse course = getById(id);
        CourseInfoVO courseInfoVO = new CourseInfoVO();
        BeanUtils.copyProperties(course, courseInfoVO);
        EduCourseDescription courseDescription = eduCourseDescriptionMapper.selectById(id);
        courseInfoVO.setDescription(courseDescription.getDescription());
        return courseInfoVO;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public boolean updateCourseInfoVOById(CourseInfoVO entity) {
        boolean flag1 = super.updateById(entity);
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(entity, description);
        int i = eduCourseDescriptionMapper.updateById(description);
        return flag1 && i > 0;
    }
}
