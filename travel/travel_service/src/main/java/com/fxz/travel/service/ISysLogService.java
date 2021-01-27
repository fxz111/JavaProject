package com.fxz.travel.service;

import com.fxz.travel.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    public void save(SysLog sysLog);

    public List<SysLog> findAll();

    public void deleteAll();
}
