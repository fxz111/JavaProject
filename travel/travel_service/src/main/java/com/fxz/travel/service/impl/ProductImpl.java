package com.fxz.travel.service.impl;

import com.fxz.travel.dao.IProductDao;
import com.fxz.travel.domain.Product;
import com.fxz.travel.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public List<Product> findByName(String productName) {
        return productDao.findByName(productName);
    }

    @Override
    public void deleteById(String[] ids) {
        for (String id:ids) {
            productDao.deleteById(id);
        }
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        System.out.println(product.getId());
        System.out.println(product.getProductPrice());
        productDao.update(product);
    }

    @Override
    public void updateById(String[] ids, Integer flag) {
        if (flag==1){
            for (String id:ids) {
                productDao.openById(id);
            }
        }
        else if (flag == 0){
            for (String id:ids) {
                productDao.closeById(id);
            }
        }
    }
}
