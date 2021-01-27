package com.fxz.travel.controller;

import com.fxz.travel.domain.Orders;
import com.fxz.travel.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrdersService iOrderService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Orders> ordersList = iOrderService.findAll();
        //PageInfo pageInfo = new PageInfo(ordersList);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orders-list");
        mv.addObject("ordersList",ordersList);
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Orders orders = iOrderService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orders-show");
        mv.addObject("orders",orders);
        return mv;
    }
}
