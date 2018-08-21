package cn.thislx.springbootmultisource.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.thislx.springbootmultisource.dao.primary", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataSourceConfig {

    /**
     * @methodDesc: 功能描述:(配置primary数据库)
     * @author: ThisLx
     * @param: @return
     * @createTime:2017年9月17日 下午3:16:44
     * @returnType:@return DataSource
     * @copyright:leimingtech.com
     * @QQ:644064779
     */
    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @methodDesc: 功能描述:(primary sql会话工厂)
     * @author: ThisLx
     * @param: @param
     * dataSource
     * @param: @return
     * @param: @throws
     * Exception
     * @createTime:2017年9月17日 下午3:17:08
     * @returnType:@param dataSource
     * @returnType:@return
     * @returnType:@throws Exception SqlSessionFactory
     * @copyright:leimingtech.com
     * @QQ:644064779
     */
    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/primary/*.xml"));
        return bean.getObject();
    }

    /**
     * @methodDesc: 功能描述:(primary 事物管理)
     * @author: ThisLx
     * @param: @param
     * dataSource
     * @param: @return
     * @param: @throws
     * Exception
     * @createTime:2017年9月17日 下午3:17:08
     * @returnType:@param dataSource
     * @returnType:@return
     * @returnType:@throws Exception SqlSessionFactory
     * @copyright:leimingtech.com
     * @QQ:644064779
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
