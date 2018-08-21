package cn.thislx.springbootjta.config.datasource;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: LX
 * @Description: basePackages 最好分开配置 如果放在同一个文件夹可能会报错
 * @Date: 2018/8/21 10:47
 * @Modified by:
 */
@Configuration
@MapperScan(basePackages = "cn.thislx.springbootjta.service.dao.primary", sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class PrimaryMyBatisConfig {

    // 配置数据源
    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource(DBPriMaryConfig dbPriMaryConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbPriMaryConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbPriMaryConfig.getPassword());
        mysqlXaDataSource.setUser(dbPriMaryConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("primaryDataSource");

        xaDataSource.setMinPoolSize(dbPriMaryConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbPriMaryConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbPriMaryConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbPriMaryConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbPriMaryConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbPriMaryConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbPriMaryConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(dbPriMaryConfig.getTestQuery());
        return xaDataSource;
    }

    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/primary/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}