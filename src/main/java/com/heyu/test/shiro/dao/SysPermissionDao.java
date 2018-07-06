package com.heyu.test.shiro.dao;

import com.heyu.test.shiro.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionDao extends CurdDao<SysPermission>{

    List<SysPermission> findByUserId(@Param("userId") String userId);
}
