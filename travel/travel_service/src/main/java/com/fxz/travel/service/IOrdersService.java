package com.fxz.travel.service;

import com.fxz.travel.domain.Orders;

import java.util.List;

public interface IOrdersService {
    public List<Orders> findAll();
    public Orders findById(String id);
}
