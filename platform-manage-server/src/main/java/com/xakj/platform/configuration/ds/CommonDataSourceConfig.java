package com.xakj.platform.configuration.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Description: TODO
 * @Author: Hardy
 * @DateTime: 2019/5/17 14:57
 * @Verstion 1.0
 */
@Configuration
@Component
@MapperScan(basePackages = CommonDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "commonSqlSessionFactory")
public class CommonDataSourceConfig {

    /**
     * 精确到 commondao 目录，以便跟其他数据源隔离
     */
    //   private static final String MAPPER_LOCATION = "classpath:mapper/**/*.xml";
    static final String PACKAGE = "com.xakj.platform.platform.mapper";

    private static final String MAPPER_LOCATION = "classpath:mapper/**/*.xml";

    @Value("${spring.datasource.druid.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.druid.url}")
    private String url;

    @Value("${spring.datasource.druid.username}")
    private String username;

    @Bean(name = "commonDataSource")
    @Primary
    public DataSource commonDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Value("${spring.datasource.druid.password}")
    private String password;

    @Bean(name = "commonTransactionManager")
    @Primary
    public DataSourceTransactionManager commonTransactionManager() {
        return new DataSourceTransactionManager(commonDataSource());
    }

    @Bean(name = "commonSqlSessionFactory")
    @Primary
    public SqlSessionFactory commonSqlSessionFactory(@Qualifier("commonDataSource") DataSource commonDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(commonDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(CommonDataSourceConfig.MAPPER_LOCATION));
        // 打印sql
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
        conf.setLogImpl(StdOutImpl.class);
        sessionFactory.setConfiguration(conf);
        return sessionFactory.getObject();
    }
}