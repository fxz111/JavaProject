package com.fxz.travel.service.impl;

import com.fxz.travel.dao.ISysLogDao;
import com.fxz.travel.domain.SysLog;
import com.fxz.travel.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISysLogServiceimpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public void deleteAll() {
        sysLogDao.deleteAll();
    }
}
