package com.fxz.eduservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class CourseQuery implements Serializable {
    @ApiModelProperty(value = "课程名称模糊查询")
    private String name;

    @ApiModelProperty(value = "发布状态 Draft未发布 Normal已发布")
    private String status;
}
