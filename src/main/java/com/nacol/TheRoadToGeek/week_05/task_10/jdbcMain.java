package com.nacol.TheRoadToGeek.week_05.task_10;

import java.sql.*;
import java.util.Random;

public class jdbcMain {



    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //statement select
        selectAll();

        //prepare statement insert
        batchInsert();

        //prepare statement update
        batchUpdate();

        //prepare statement delete
        batchDelete();
    }

    private static final String DELETE_SQL = "delete from sys_user where age > ?";
    private static void batchDelete() throws SQLException, ClassNotFoundException {
        try(Connection conn = SimpJdbcUtils.getConnection();
            PreparedStatement preStm  = conn.prepareStatement(DELETE_SQL)) {
            for (int i = 0; i < 3; i++) {
                preStm.setInt(1, 8);
                preStm.execute();
            }
        }
    }

    /**
     * select
     */
    private static void selectAll() throws ClassNotFoundException, SQLException {
        try(Statement stm = SimpJdbcUtils.getStatement();
            ResultSet rs = stm.executeQuery("SELECT user_name, age FROM sys_user")) {
            while(rs.next()){
                System.out.println(rs.getString("user_name")+" 年龄："+rs.getInt("age"));
            }
        }
    }

    /**
     * insert
     */
    private static final String INSERT_SQL = "insert into sys_user (user_name, age) values (?, ?)";
    private static void batchInsert() throws SQLException, ClassNotFoundException {
        try(Connection conn = SimpJdbcUtils.getConnection();
            PreparedStatement preStm  = conn.prepareStatement(INSERT_SQL)) {
            for (int i = 0; i < 3; i++) {
                preStm.setString(1, "nacol-" + System.currentTimeMillis());
                preStm.setInt(2, 19);
                preStm.execute();
            }
        }
    }

    /**
     * update
     */
    private static final String UPDATE_SQL = "update sys_user set age = ? ";
    private static void batchUpdate() throws SQLException, ClassNotFoundException {
        try(Connection conn = SimpJdbcUtils.getConnection();
            PreparedStatement preStm  = conn.prepareStatement(UPDATE_SQL)) {
            preStm.setInt(1, new Random().nextInt(19));
            preStm.execute();
        }
    }
}
