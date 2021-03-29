package com.fxz.msm.controller;

import com.fxz.commonutils.R;
import com.fxz.msm.MsmApplication;
import com.fxz.msm.service.MsmService;
import com.fxz.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("msmservice/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //发送短信
    @GetMapping("sendMsm/{phoneNum}")
    public R sendMsm(@PathVariable String phoneNum){
        //先从redis获取
        String code = redisTemplate.opsForValue().get(phoneNum);
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //获取不到在进行阿里云发送
        //先生成随机验证码
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        Boolean result = msmService.send(param,phoneNum);
        if (result){
            //设置有效时常 5分钟
            redisTemplate.opsForValue().set(phoneNum,code,5,TimeUnit.MINUTES);
            return R.ok();
        }else{
            return R.error().message("短信发送失败");
        }
    }
}
