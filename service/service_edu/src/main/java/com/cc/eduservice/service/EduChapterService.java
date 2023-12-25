package com.cc.eduservice.service;

import com.cc.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cc
 * @since 2023-12-24
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteById(String chapterId);
}
