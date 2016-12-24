package com.yiheidaodi.zkweb.util;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * zkweb com.yiheidaodi.zkweb
 * 描述：
 * 时间：2016-12-24 14:54.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSQLite {

    @Autowired
    private DataSource ds;

    @Test
    @Ignore
    public void createTable() throws SQLException {
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE zk_connection1 " +
                "(uuid TEXT(50) PRIMARY KEY NOT NULL," +
                " name TEXT(50) NOT NULL, " +
                " ip TEXT(50)  NOT NULL, " +
                " port TEXT(5) NOT NULL ) ";
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    @Test
    @Ignore
    public void testResultSet() throws SQLException {
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from zk_connection";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(resultSet.next());
        statement.close();
        connection.close();
    }
}
