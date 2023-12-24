package com.cc.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.eduservice.entity.vo.TeacherQueryVO;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author cc
 * @since 2023-05-26
 */
public interface EduTeacherService extends IService<EduTeacher> {
    IPage<EduTeacher> page(IPage<EduTeacher> page, TeacherQueryVO queryVO);
}
