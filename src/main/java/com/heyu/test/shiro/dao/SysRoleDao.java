package com.heyu.test.shiro.dao;

import com.heyu.test.shiro.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleDao extends CurdDao<SysRole>{

    List<SysRole> findByUserId(@Param("userId") String userId);
}
