package com.cityu.defect.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//@EnableOpenApi//该注解是Springfox-swagger框架提供的使用Swagger注解，该注解必须加
@EnableSwagger2WebMvc
//@EnableKnife4j//该注解是knife4j提供的增强注解,Ui提供了例如动态参数、参数过滤、接口排序等增强功能,如果你想使用这些增强功能就必须加该注解，否则可以不用加
@Configuration
public class Swagger3Configuration {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                //用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                //设置哪些接口暴露给Swagger展示
                .select()
                // (第一种方式)扫描所有有注解的api，用这种方式更灵活
                .apis( RequestHandlerSelectors.withMethodAnnotation( ApiOperation.class ) )

                // (第二种方式)扫描指定包中的swagger注解
//                .apis(RequestHandlerSelectors.basePackage("com.cityu.defect"))//构建API

                // (第三种方式)扫描所有
//                .apis(RequestHandlerSelectors.any())
                .paths( PathSelectors.any() )
                .build()
//                .groupName("XimenesChen")
                .enable(true);
    }

    /**
     * 添加摘要信息
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Defect Manage Tool")//标题
                .description("The backend of the Defect Manage Tool For the Final Project of CS5351 in CityU")//描述
                .contact(new Contact("Coding After Waking up", "https://github.com/Chen30lv/Project-Defect-Manege-Tool", "123@qq.com"))
                .version("V1.00")
                .build();
    }
}


