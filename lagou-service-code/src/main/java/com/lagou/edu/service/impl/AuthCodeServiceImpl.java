package com.lagou.edu.service.impl;

import com.lagou.edu.dao.IAuthCodeDao;
import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.service.AuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class AuthCodeServiceImpl implements AuthCodeService {


    @Autowired
    private IAuthCodeDao iAuthCodeDao;


    @SuppressWarnings("all")
    @Override
    public boolean saveAuthCode(String email,String code) {
        Date date = new Date();
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(email);
        lagouAuthCode.setCode(code);
        lagouAuthCode.setCreatetime(date);
        lagouAuthCode.setExpiretime(new Date(date.getTime() + TimeUnit.MINUTES.toMillis(10)));
        LagouAuthCode save = iAuthCodeDao.save(lagouAuthCode);
        return save!=null;
    }

    @Override
    public LagouAuthCode getAuthCode(String email,String code) {
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(email);
        lagouAuthCode.setCode(code);
        Example<LagouAuthCode> example = Example.of(lagouAuthCode);
        List<LagouAuthCode> list = iAuthCodeDao.findAll(example);
        return list.size()!=0?list.get(list.size()-1):null;
    }
}
