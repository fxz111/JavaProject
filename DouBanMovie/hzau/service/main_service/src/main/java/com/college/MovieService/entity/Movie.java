package com.college.MovieService.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Movie对象", description="")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("Movie_url")
    private String movieUrl;

    @TableField("Movie_name")
    private String movieName;

    @TableField("Movie_type")
    private String movieType;

    @TableField("Movie_area")
    private String movieArea;

    @TableField("Movie_length")
    private Integer movieLength;

    @TableField("Movie_time")
    private String movieTime;

    @TableField("Movie_score")
    private Double movieScore;

    @TableField("Movie_person")
    private Integer moviePerson;

    @TableField("Movie_director")
    private String movieDirector;

    @TableField("Movie_actor")
    private String movieActor;

    @TableField("Movie_introduce")
    private String movieIntroduce;

    @TableField("Review_amount")
    private Integer reviewAmount;


}
