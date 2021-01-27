package com.fxz.travel.controller;

import com.fxz.travel.domain.SysLog;
import com.fxz.travel.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
@Aspect
@Component
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.fxz.travel.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); //只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method= clazz.getMethod(methodName, classArgs);
        }
    }

    //后置通知
    @After("execution(* com.fxz.travel.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        // 获取类上的@RequestMapping对象
        if (clazz != SysLogController.class) {
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                 // 获取方法上的@RequestMapping对象
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);

                if (methodAnnotation != null) {

                    String url = "";
                    url = classAnnotation.value()[0] + methodAnnotation.value()[0];
                    SysLog sysLog = new SysLog();
// 获取访问时长
                    Long executionTime = new Date().getTime() - visitTime.getTime();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUrl(url);

                    String ip = request.getRemoteAddr();
                    sysLog.setIp(ip);

// 可以通过securityContext获取，也可以从request.getSession中获取
                    SecurityContext context = SecurityContextHolder.getContext(); // request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
                    String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();
                    sysLog.setUsername(username);

                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());

                    sysLog.setVisitTime(visitTime);

                    sysLogService.save(sysLog);
                }


            }
        }}}
