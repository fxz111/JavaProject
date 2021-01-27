package com.fxz.travel.controller;

import com.fxz.travel.domain.Product;
import com.fxz.travel.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(productList);
        mv.setViewName("product-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findByName.do")
    public ModelAndView findByName(@RequestParam String productName){
        ModelAndView mv = new ModelAndView();
        //System.out.println(productName);
        List<Product> productList = productService.findByName(productName);
        mv.setViewName("product-search");
        mv.addObject("productList",productList);
        return mv;
    }
    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name = "ids") String[] ids){
        System.out.println(ids);
        productService.deleteById(ids);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam String id){
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        mv.setViewName("product-update");
        mv.addObject("product",product);
        return mv;
    }
    @RequestMapping("/update.do")
    public String update(Product product,@RequestParam String id){
        product.setId(id);
       productService.update(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("/updateById.do")
    public String updateById(@RequestParam(name = "ids")String[] ids,@RequestParam Integer flag){
        System.out.println(flag);
      productService.updateById(ids,flag);
        return "redirect:findAll.do";
    }

}
