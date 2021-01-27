package com.fxz.travel.service;

import com.fxz.travel.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll(Integer page,Integer pageSize);
    public void save(Product product);
    public List<Product> findByName(String productName);
    public void deleteById(String[] ids);

    public Product findById(String id);

    public void update(Product product);

    void updateById(String[] ids, Integer flag);
}
