package com.lagou.edu.service.impl;

import com.lagou.edu.dao.ITokenDao;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ITokenServerImpl implements ITokenService {


    @Autowired
    private ITokenDao iTokenDao;

    @Override
    public LagouToken register(String email, String password, String code) {

        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        //根据UUID生成token
        lagouToken.setToken(UUID.nameUUIDFromBytes((email + password).getBytes()).toString());
        LagouToken saveToken = iTokenDao.save(lagouToken);
        return saveToken;
    }

    @Override
    public boolean isRegistered(String email) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        Example<LagouToken> example = Example.of(lagouToken);
        List<LagouToken> all = iTokenDao.findAll(example);
        LagouToken tempTomken = all.size()!=0?all.get(0):null;
        return tempTomken!=null;
    }

    @Override
    public LagouToken login(String email, String password) {

        LagouToken lagouToken = new LagouToken();
        lagouToken.setToken(UUID.nameUUIDFromBytes((email + password).getBytes()).toString());
        Example<LagouToken> example = Example.of(lagouToken);
        List<LagouToken> all = iTokenDao.findAll(example);
        LagouToken tempTomken = all.size()!=0?all.get(0):null;
        return tempTomken;
    }

    @Override
    public String info(String token) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setToken(token);
        Example<LagouToken> example = Example.of(lagouToken);
        List<LagouToken> all = iTokenDao.findAll(example);
        LagouToken tempTomken = all.size()!=0?all.get(0):null;
        return tempTomken!=null?tempTomken.getEmail():null;
    }
}
