package com.heyu.sysuser.service;

import com.heyu.sysuser.dao.SysUserDao;
import com.heyu.sysuser.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public List<SysUser> selectByLoginName(String loginName){
        return sysUserDao.selectByLoginName(loginName);
    }

}
