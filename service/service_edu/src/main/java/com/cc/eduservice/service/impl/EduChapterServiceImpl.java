package com.cc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.eduservice.entity.EduChapter;
import com.cc.eduservice.entity.EduVideo;
import com.cc.eduservice.entity.chapter.ChapterVo;
import com.cc.eduservice.entity.chapter.VideoVo;
import com.cc.eduservice.mapper.EduChapterMapper;
import com.cc.eduservice.service.EduChapterService;
import com.cc.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cc
 * @since 2023-12-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        // 1. 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        // 2. 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(queryWrapper2);

        // 创建结果
        List<ChapterVo> finalList = new ArrayList<>();
        eduChapterList.forEach(chapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVo.setChildren(eduVideoList.stream().filter(video -> Objects.equals(chapterVo.getId(), video.getChapterId())).map(video -> {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                return videoVo;
            }).collect(Collectors.toList()));
            finalList.add(chapterVo);
        });
        return finalList;
    }
}
