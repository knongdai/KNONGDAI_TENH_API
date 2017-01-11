package com.knongdai.tinh.configuration;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@MapperScan({ "com.knongdai.tinh.repositories" })
@EnableSwagger
public class PsarnetConfiguration {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public DataSourceTransactionManager transactionManaget(){
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(){
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory;
	}
	
	private SpringSwaggerConfig springSwaggerConfig;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }
	
	
    	@Bean
	    public SwaggerSpringMvcPlugin customImplementation(){

	        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
	                .apiInfo(apiInfo());
	             //  .includePatterns(".*api.*"); // assuming the API lives at something like http://myapp/api
	    }

	    private ApiInfo apiInfo() {
	        ApiInfo apiInfo = new ApiInfo(
	                "Tos Tenh" ,
//	                "API KEY : "+header+"  (eg. header {'Authorization' , 'Basic "+header+"'} )",
	                "API KEY : Please contact to WS Developer for Key",
	                "KA API",
	                "info.kshrd@gmail.com",
	                "API License",
	                "http://khmeracademy.org"
	        );        
	        return apiInfo;
	    }
	
	
	
	
}
