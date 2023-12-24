package com.cc.eduservice.service;

import com.cc.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author cc
 * @since 2023-06-24
 */
public interface EduSubjectService extends IService<EduSubject> {

    void readAndAdd(MultipartFile file) throws IOException;

    List<EduSubject> findTree();

    List<EduSubject> findSubjectByParentId(String parentId);
}
