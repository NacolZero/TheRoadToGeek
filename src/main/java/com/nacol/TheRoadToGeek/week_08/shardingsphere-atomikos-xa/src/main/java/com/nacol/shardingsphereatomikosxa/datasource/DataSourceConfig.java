package com.nacol.shardingsphereatomikosxa.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource sdDataSource() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第 1 个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/geek0?useUnicode=true&characterEncoding=utf8&tinyInt1isBit=false&useSSL=false&serverTimezone=GMT");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("geek0", dataSource1);

        // 配置第 2 个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/geek1?useUnicode=true&characterEncoding=utf8&tinyInt1isBit=false&useSSL=false&serverTimezone=GMT");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("geek1", dataSource2);

        // 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig =
                new ShardingTableRuleConfiguration("order_payment", "geek${0..1}.order_payment_${0..9}");

        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration("order_base_id", "dbShardingAlgorithm"));

        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(
                new StandardShardingStrategyConfiguration("id", "tableShardingAlgorithm"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "geek${order_base_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "order_payment_${id % 10}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());
        return dataSource;
    }

}
