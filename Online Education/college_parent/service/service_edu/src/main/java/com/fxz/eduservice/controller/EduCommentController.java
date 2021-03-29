package com.fxz.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.commonutils.JwtUtils;
import com.fxz.commonutils.R;
import com.fxz.eduservice.client.MemberClient;
import com.fxz.eduservice.entity.EduComment;
import com.fxz.eduservice.entity.vo.MemberWeb;
import com.fxz.eduservice.service.EduCommentService;
import com.fxz.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-06
 */
@RestController
@RequestMapping("/eduservice/educomment")
@CrossOrigin
public class EduCommentController {
    @Autowired
    private MemberClient memberClient;
    @Autowired
    private EduCommentService commentService;
    //分页查询评论信息
    @PostMapping("pageComment/{page}/{limit}/{courseId}")
    public R pageComment(@PathVariable Long page,
                         @PathVariable Long limit,
                         @PathVariable String courseId){
        Page<EduComment> commentPage = new Page<>(page,limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_create");
        commentService.page(commentPage,wrapper);
        List<EduComment> items = commentPage.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("items",items);
        map.put("current", commentPage.getCurrent());
        map.put("pages", commentPage.getPages());
        map.put("size", commentPage.getSize());
        map.put("total", commentPage.getTotal());
        map.put("hasNext", commentPage.hasNext());
        map.put("hasPrevious",commentPage.hasPrevious());
        return R.ok().data(map);
    }

    //添加评论
    @PostMapping("addComment")
    public R addComment(@RequestBody EduComment comment, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(memberId==null){
            throw new GuliException(20001,"未登录");
        }
        MemberWeb memberWeb = memberClient.getInfoById(memberId);
        comment.setMemberId(memberId);
        comment.setNickname(memberWeb.getNickname());
        comment.setAvatar(memberWeb.getAvatar());
        commentService.save(comment);
        return R.ok();

    }

    //测试用
    @GetMapping("ceshi/{id}")
    public R ceshi(@PathVariable String id){
        MemberWeb memberWeb = memberClient.getInfoById(id);
        return R.ok().data("ceshi",memberWeb);
    }

}

