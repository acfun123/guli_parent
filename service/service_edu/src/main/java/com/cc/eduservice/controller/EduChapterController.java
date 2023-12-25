package com.cc.eduservice.controller;


import com.cc.Result;
import com.cc.eduservice.entity.EduChapter;
import com.cc.eduservice.entity.chapter.ChapterVo;
import com.cc.eduservice.service.EduChapterService;
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
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("allChapterVideo", list);
    }

    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter chapter) {
        eduChapterService.save(chapter);
        return Result.ok();
    }

    @GetMapping("getChapterById/{chapterId}")
    public Result getChapterInfo(@PathVariable String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return Result.ok().data("chapter", chapter);
    }

    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter chapter) {
        eduChapterService.updateById(chapter);
        return Result.ok();
    }

    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId) {
        return eduChapterService.deleteById(chapterId) ? Result.ok() : Result.error().message("请先将章节下的小节删除");
    }
}

