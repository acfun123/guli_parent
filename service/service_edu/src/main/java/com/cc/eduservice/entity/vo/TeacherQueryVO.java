package com.cc.eduservice.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherQueryVO {
    private String name;
    private Integer level;

    private Date start;

    private Date end;
}
