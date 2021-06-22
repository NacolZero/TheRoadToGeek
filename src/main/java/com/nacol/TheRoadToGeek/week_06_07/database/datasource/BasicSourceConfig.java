package com.nacol.TheRoadToGeek.week_06_07.database.datasource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@Configuration
public class BasicSourceConfig {

    @Bean(name = "basicForMySQLDataSource")
    public DataSource basicForMySQLDataSource() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/geek?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        return BasicDataSourceFactory.createDataSource(properties);
    }

    @Bean(name = "basicForPgSQLDataSource")
    public DataSource basicForPgSQLDataSource() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "org.postgresql.Driver");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/geek");
        properties.setProperty("username", "postgres");
        properties.setProperty("password", "123456");
        return BasicDataSourceFactory.createDataSource(properties);
    }

    static String url = "jdbc:postgresql://127.0.0.1:5432/geek";
    static String usr = "postgres";
    static String psd = "123456";
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, usr, psd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM order_base");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print("  ");
                System.out.println(rs.getString(2));

            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
