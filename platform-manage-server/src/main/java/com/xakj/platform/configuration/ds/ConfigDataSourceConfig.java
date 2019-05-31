package com.xakj.platform.configuration.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Configuration
@Component
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = ConfigDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "configSqlSessionFactory")
public class ConfigDataSourceConfig {

    // 精确到 com.xakj.ant.platform.mapper 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.xakj.platform.InsuranceSide.mapper";
    static final String MAPPER_LOCATION = "classpath:platform/*.xml";

    @Value("${spring.datasource.slaver.url}")
    private String url;

    @Value("${spring.datasource.slaver.username}")
    private String user;

    @Value("${spring.datasource.slaver.password}")
    private String password;

    @Value("${spring.datasource.slaver.driver-class-name}")
    private String driverClass;

    @Bean(name = "configDataSource")
    public DataSource configDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "configTransactionManager")
    public DataSourceTransactionManager configTransactionManager() {
        return new DataSourceTransactionManager(configDataSource());
    }

    @Bean(name = "configSqlSessionFactory")
    public SqlSessionFactory configSqlSessionFactory(@Qualifier("configDataSource") DataSource configDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(configDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ConfigDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
