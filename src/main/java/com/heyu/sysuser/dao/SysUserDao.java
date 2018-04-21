package com.heyu.sysuser.dao;

import com.heyu.sysuser.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *继承JpaRepository<SysUser,String>，添加默认的增删改查功能,String标识主键类型
 */
@Component
@Mapper
public interface SysUserDao{

    public List<SysUser> selectByLoginName(@Param(value = "loginName") String loginName);

}
