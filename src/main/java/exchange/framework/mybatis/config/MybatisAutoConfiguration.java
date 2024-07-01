package exchange.framework.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import exchange.framework.mybatis.core.handler.DefaultDBFieldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBaits 配置类
 *
 */
//@AutoConfiguration
//@MapperScan(value = "exchange.userpg", annotationClass = Mapper.class) // Mapper 懒加载，目前仅用于单元测试
@Configuration
public class MybatisAutoConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor()); // 分页插件
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultDBFieldHandler(); // 自动填充参数类
    }

//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
//        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//
//        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setMetaObjectHandler(new DefaultDBFieldHandler());
//        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
//        return mybatisSqlSessionFactoryBean;
//    }
}
