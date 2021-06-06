package com.nacol.TheRoadToGeek.week_05.task_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpJdbcUtils {

    private static String URL = "jdbc:mysql://localhost:3306/spring_buckets?serverTimezone=UTC&useSSL=false";
    private static String USER = "root";
    private static String PASSWORD = "Jy013828230.";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        //3.操作数据库，实现增删改查
        Statement stm = conn.createStatement();
        return stm;
    }

}
