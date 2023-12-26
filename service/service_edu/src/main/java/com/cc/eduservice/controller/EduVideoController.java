package com.cc.eduservice.controller;


import com.cc.Result;
import com.cc.eduservice.entity.EduVideo;
import com.cc.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author cc
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService eduVideoService;

    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo video) {
        eduVideoService.save(video);
        return Result.ok();
    }

    @GetMapping("getVideoById/{videoId}")
    public Result getVideoInfo(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        return Result.ok().data("video", video);
    }

    @PostMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo video) {
        eduVideoService.updateById(video);
        return Result.ok();
    }

    @DeleteMapping("{videoId}")
    public Result deleteChapter(@PathVariable String videoId) {
        eduVideoService.removeById(videoId);
        return Result.ok();
    }
}

