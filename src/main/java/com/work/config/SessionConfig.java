package com.work.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author wq
 * @desc session配置类
 */
@Configuration
//@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class SessionConfig {

    //SqlSession配置
    private Logger logger = LoggerFactory.getLogger(SessionConfig.class);

    @Value("${spring.datasource.jndi-name}")
    private String dataSourceJndiName;

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;

    @Bean
    public SqlSessionFactoryBean createSqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = null;
        try {
            // 加载JNDI配置
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(dataSourceJndiName);

            // 实例SessionFactory
            sqlSessionFactoryBean = new SqlSessionFactoryBean();
            // 配置数据源
            sqlSessionFactoryBean.setDataSource(dataSource);

            // 加载MyBatis配置文件
            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
            sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));
            // 配置mybatis的config文件
            // sqlSessionFactoryBean.setConfigLocation("mybatis-config.xml");
        } catch (Exception e) {
            logger.error("创建SqlSessionFactoryBean失败", e);
        }
        return sqlSessionFactoryBean;
    }

    //设定cookie序列化器的属性
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        //cookies名称
        serializer.setCookieName("JSESSIONID");
        //用正则表达式配置匹配的域名，可以兼容localhost、127.0.0.1等各种场景
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");//cookies作用域
        //cookie生效路径
        serializer.setCookiePath("/");
        //设置是否只能通过服务器修改，浏览器端不能修改
        serializer.setUseHttpOnlyCookie(false);
        //最大生命周期的单位是分钟
        serializer.setCookieMaxAge(60 * 60 * 24);
        return serializer;
    }

    //注册序列化器
    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }

}
