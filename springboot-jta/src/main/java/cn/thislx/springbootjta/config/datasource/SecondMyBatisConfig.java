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

@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "cn.thislx.springbootjta.service.dao.second", sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class SecondMyBatisConfig {

    // 配置数据源
    @Bean(name = "secondDataSource")
    public DataSource testDataSource(DBSecondConfig dbSecondConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbSecondConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbSecondConfig.getPassword());
        mysqlXaDataSource.setUser(dbSecondConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("secondDataSource");

        xaDataSource.setMinPoolSize(dbSecondConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbSecondConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbSecondConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbSecondConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbSecondConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbSecondConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbSecondConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(dbSecondConfig.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/second/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}