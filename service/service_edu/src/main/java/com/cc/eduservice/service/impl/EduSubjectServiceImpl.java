package com.cc.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.eduservice.config.EduSubjectListener;
import com.cc.eduservice.entity.EduSubject;
import com.cc.eduservice.mapper.EduSubjectMapper;
import com.cc.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author cc
 * @since 2023-06-24
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void readAndAdd(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), EduSubject.class, new EduSubjectListener(this)).sheet().doRead();
    }

    @Override
    public List<EduSubject> findTree() {
        long start = System.currentTimeMillis();
        List<EduSubject> res = new ArrayList<>();
        // 1.先查出所有科目
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("parent_id");
        List<EduSubject> list = list(null);
        // 2.组装成树结构
        fillChildrenById(res, "0", list);
        System.out.println(System.currentTimeMillis() - start);
        return res;
    }

    @Override
    public List<EduSubject> findSubjectByParentId(String parentId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        return list(queryWrapper);
    }

    private void fillChildrenById(List<EduSubject> res, String id, List<EduSubject> list) {
        // 1. 根据id筛选出parent_id为id的项，填充到res中
        res.addAll(list.stream().filter(e -> id.equals(e.getParentId())).collect(Collectors.toList()));
        // 2. 遍历res, 根据id筛选出parent_id为id的项，填充到children中
        res.forEach(e -> {
            e.setChildren(new ArrayList<>());
            fillChildrenById(e.getChildren(), e.getId(), list);
        });
    }

}
