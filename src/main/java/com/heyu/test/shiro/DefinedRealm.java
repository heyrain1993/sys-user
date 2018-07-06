package com.heyu.test.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.heyu.test.shiro.dao.SysPermissionDao;
import com.heyu.test.shiro.dao.SysRoleDao;
import com.heyu.test.shiro.dao.SysUserDao;
import com.heyu.test.shiro.model.SysPermission;
import com.heyu.test.shiro.model.SysRole;
import com.heyu.test.shiro.model.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 测试自定义Realm的认证方式
 */
@Component
public class DefinedRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权中。。。。。" + principalCollection.toString());

        SysUser sysUser = sysUserDao.findByUsername((String) principalCollection.getPrimaryPrincipal());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //查询用户拥有角色
        List<SysRole> sysRoles = sysRoleDao.findByUserId(sysUser.getId());
        if(sysRoles != null && sysRoles.size() != 0){
            for (SysRole sysRole:sysRoles){
                info.addRole(sysRole.getName());
            }
        }

        //查询用户所拥有的权限
        List<SysPermission> sysPermissionss = sysPermissionDao.findByUserId(sysUser.getId());
        if(sysPermissionss != null && sysPermissionss.size() != 0){
            for (SysPermission sysPermission:sysPermissionss){
                info.addStringPermission(sysPermission.getName());
            }

        }

        return info;
    }

    /**
     * 自定义认证方式
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        /*Map<String,String> map = new HashMap<>();
        map.put("driverClassName","com.mysql.jdbc.Driver");
        map.put("url","jdbc:mysql://127.0.0.1:3306/rdsweb?characterEncoding=utf-8");
        map.put("username","root");
        map.put("password","123456");

        String password = null;
        try {
            //创建数据源
            DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(map);
            Connection connection = druidDataSource.getConnection();
            String sql = "select password from users where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, (String) authenticationToken.getPrincipal());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                password = resultSet.getString(1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = (String) token.getPrincipal();
        SysUser sysUser = sysUserDao.findByUsername(username);
        if(sysUser == null){
            return null;
        }
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),sysUser.getPassword(),this.getName());


    }
}
