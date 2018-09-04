package com.heyu.test.iBatis;

import com.heyu.test.shiro.model.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class JdbcTest {

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/rdsweb";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public SysUser getUser(String id){
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("select id, username, password from sys_user where id = ?");
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                SysUser user =  new SysUser();
                user.setId(resultSet.getString(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void close(ResultSet resultSet,Statement statement,Connection connection){
        try {
            if(resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }

            if(statement != null && !statement.isClosed()){
                resultSet.close();
            }

            if(connection != null && !connection.isClosed()){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        JdbcTest test = new JdbcTest();
        SysUser user = test.getUser("1");
        System.out.println(user.getPassword());

        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = null;
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
