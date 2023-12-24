package com.cc.eduservice.entity.vo;

import com.cc.eduservice.entity.EduCourse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseInfoVO extends EduCourse {
    private String description;
}
