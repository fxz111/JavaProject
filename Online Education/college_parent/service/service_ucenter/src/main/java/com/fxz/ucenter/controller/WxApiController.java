package com.fxz.ucenter.controller;

import com.fxz.commonutils.JwtUtils;
import com.fxz.servicebase.exceptionhandler.GuliException;
import com.fxz.ucenter.entity.Member;
import com.fxz.ucenter.service.MemberService;
import com.fxz.ucenter.utils.ConstantWxUtils;
import com.fxz.ucenter.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private MemberService memberService;
    @GetMapping("callback")
    public String callback(String code,String state){
        try{
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            //用地址得到token和openid
            String tokenInfo = HttpClientUtils.get(accessTokenUrl);
            //System.out.println(tokenInfo);
            Gson gson = new Gson();
            HashMap mapToken = gson.fromJson(tokenInfo,HashMap.class);
            String access_token = (String)mapToken.get("access_token");
            String openid = (String)mapToken.get("openid");
            //先判断数据库是否相同openid
            Member member = memberService.getByOpenId(openid);
            if (member == null){
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                String userInfo = HttpClientUtils.get(userInfoUrl);
                //System.out.println(userInfo);
                HashMap userMap = gson.fromJson(userInfo,HashMap.class);
                String nickname = (String) userMap.get("nickname");
                String headimgurl = (String) userMap.get("headimgurl");
                member = new Member();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }
            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            //最后：返回首页面，通过路径传递token字符串
            return "redirect:http://localhost:3000?token="+jwtToken;
            //return "1";
        }catch (Exception e){
            throw new GuliException(20001,"登录失败");
        }
    }

    //生成二维码
    @GetMapping("login")
    public String getWxCode(){
        String baseurl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s"+
                "&redirect_uri=%s"+
                "&response_type=code"+
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        }catch(Exception e) {
        }
        //设置%s值
        String url = String.format(
                baseurl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu"
        );

        //先请求地址
        return "redirect:"+url;
    }
}
