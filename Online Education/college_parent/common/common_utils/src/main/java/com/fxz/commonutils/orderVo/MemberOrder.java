package com.fxz.commonutils.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberOrder {
    @ApiModelProperty(value = "会员ID")
    private String id;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "手机号")
    private String mobile;
}