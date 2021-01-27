package com.fxz.travel.service.impl;

import com.fxz.travel.dao.ITravellerDao;
import com.fxz.travel.domain.Traveller;
import com.fxz.travel.service.ITravellerService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerServiceimpl implements ITravellerService {
    @Autowired
    private ITravellerDao travellerDao;

    @Override
    public List<Traveller> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return travellerDao.findAll();
    }

    @Override
    public void save(Traveller traveller) {
        travellerDao.save(traveller);
    }

    @Override
    public void deleteById(String[] ids) {
        for (String id:ids) {
            travellerDao.deleteById(id);
        }
    }

    @Override
    public List<Traveller> findByName(String name) {
        return travellerDao.findByName(name);
    }

    @Override
    public Traveller findById(String id) {
        return travellerDao.findById(id);
    }

    @Override
    public void update(Traveller traveller) {
        travellerDao.update(traveller);
    }
}
