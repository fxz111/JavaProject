package com.college.DreService.entity;

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
@ApiModel(value="Dremovie对象", description="")
public class Dremovie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("Movie_director")
    private String movieDirector;

    private Integer bad;

    private Integer mid;

    private Integer good;


}
