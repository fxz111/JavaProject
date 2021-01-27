package com.fxz.travel.service;

import com.fxz.travel.domain.Traveller;

import java.util.List;

public interface ITravellerService {
    List<Traveller> findAll(Integer page, Integer pageSize);

    void save(Traveller traveller);

    void deleteById(String[] ids);

    List<Traveller> findByName(String name);

    Traveller findById(String id);

    void update(Traveller traveller);
}
