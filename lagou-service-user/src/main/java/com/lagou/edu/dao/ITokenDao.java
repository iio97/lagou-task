package com.lagou.edu.dao;

import com.lagou.edu.pojo.LagouToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenDao extends JpaRepository<LagouToken,Integer> {




}
