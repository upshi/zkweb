package com.yiheidaodi.zkweb.util;

import com.yiheidaodi.zkweb.domain.ZKConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * zkweb com.yiheidaodi.zkweb.util
 * 描述：
 * 时间：2016-12-24 14:40.
 */

@Component
public class SQLiteUtil {

    @Autowired
    private DataSource ds;

    private static final String SQL_ADD = "insert into zk_connection values(?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "select * from zk_connection";
    private static final String SQL_SELECT_BY_UUID = "select * from zk_connection where uuid = ?";
    private static final String SQL_DELETE_BY_UUID = "delete from zk_connection where uuid = ?";

    private Connection getConn() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private Statement getStat(Connection conn) {
        Statement stat = null;
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }

    private PreparedStatement getPstat(Connection conn, String sql) {
        PreparedStatement pstat = null;
        try {
            pstat = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstat;
    }

    public int add(ZKConnection zkConn) {
        Connection conn = getConn();
        PreparedStatement pstat = getPstat(conn, SQL_ADD);
        int count = 0;
        try {
            pstat.setString(1, zkConn.getUuid());
            pstat.setString(2, zkConn.getName());
            pstat.setString(3, zkConn.getIp());
            pstat.setString(4, zkConn.getPort());
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstat);
            close(conn);
        }
        return count;
    }

    public ZKConnection selectByUuid(String uuid) {
        Connection conn = getConn();
        PreparedStatement pstat = getPstat(conn, SQL_SELECT_BY_UUID);
        ResultSet rs = null;
        ZKConnection zkConn = null;
        try {
            pstat.setString(1, uuid);
            rs = pstat.executeQuery();
            if(rs.next()) {
                zkConn = new ZKConnection();
                zkConn.setUuid(rs.getString(1));
                zkConn.setName(rs.getString(2));
                zkConn.setIp(rs.getString(3));
                zkConn.setPort(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstat);
            close(conn);
        }
        return zkConn;
    }

    public int deleteByUuid(String uuid) {
        Connection conn = getConn();
        PreparedStatement pstat = getPstat(conn, SQL_DELETE_BY_UUID);
        int count = 0;
        try {
            pstat.setString(1, uuid);
            count = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstat);
            close(conn);
        }
        return count;
    }

    public List<ZKConnection> selectAll() {
        Connection conn = getConn();
        Statement stat = getStat(conn);
        ResultSet rs = null;
        List<ZKConnection> list = new ArrayList<>();
        ZKConnection zkConn = null;
        try {
            rs = stat.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                zkConn = new ZKConnection();
                zkConn.setUuid(rs.getString(1));
                zkConn.setName(rs.getString(2));
                zkConn.setIp(rs.getString(3));
                zkConn.setPort(rs.getString(4));
                list.add(zkConn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(stat);
            close(conn);
        }
        return list;
    }

    public void createTable() throws SQLException {
        Connection conn = getConn();
        Statement stat = getStat(conn);
        String sql = "CREATE TABLE zk_connection1 " +
                "(uuid TEXT(50) PRIMARY KEY NOT NULL," +
                " name TEXT(50) NOT NULL, " +
                " ip TEXT(50)  NOT NULL, " +
                " port TEXT(5) NOT NULL ) ";
        stat.executeUpdate(sql);
        close(stat);
        close(conn);
    }

    private void close(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Statement stat) {
        if(stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(PreparedStatement pstat) {
        if(pstat != null) {
            try {
                pstat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
