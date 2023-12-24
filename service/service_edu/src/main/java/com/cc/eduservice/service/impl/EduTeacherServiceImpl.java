package com.cc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.eduservice.entity.EduTeacher;
import com.cc.eduservice.entity.vo.TeacherQueryVO;
import com.cc.eduservice.mapper.EduTeacherMapper;
import com.cc.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author cc
 * @since 2023-05-26
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    public IPage<EduTeacher> page(IPage<EduTeacher> page, TeacherQueryVO queryVO) {
        //多条件组合查询
        String name = queryVO.getName();
        Integer level = queryVO.getLevel();
        Date start = queryVO.getStart();
        Date end = queryVO.getEnd();
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (level != null) {
            queryWrapper.eq("level", level);
        }
        if (start != null) {
            queryWrapper.ge("gmt_create", start);
        }
        if (end != null) {
            queryWrapper.le("gmt_create", end);
        }
        return super.page(page, queryWrapper);
    }
}
