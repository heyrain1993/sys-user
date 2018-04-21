package com.heyu.sysuser.service;

import com.heyu.sysuser.dao.SysUserDao;
import com.heyu.sysuser.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Transactional
    public void insert(){
        SysUser user1 = new SysUser();
        user1.setLoginName("aa");
        user1.setPassword("12");
        user1.setEnabled("0");


        SysUser user2 = new SysUser();
        user2.setLoginName("aaaaaaa");
        user2.setPassword("12");
        user2.setEnabled("0");
    }

    public List<SysUser> selectByLoginName(String loginName){
        return sysUserDao.selectByLoginName(loginName);
    }
}
