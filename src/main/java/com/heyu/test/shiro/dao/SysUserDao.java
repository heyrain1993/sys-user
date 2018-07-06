package com.heyu.test.shiro.dao;

import com.heyu.test.shiro.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserDao extends CurdDao<SysUser>{

	SysUser findByUsername(@Param("username") String username);
}
