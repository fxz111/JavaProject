package com.fxz.ucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberWeb {
    @ApiModelProperty(value = "会员ID")
    private String id;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String avatar;
}
