package com.acompe.config;


import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        //开发者接口
        List<VendorExtension> list = new ArrayList<>();
        list.add(new StringVendorExtension("闫国雨","yanguoyu"));

        return new ApiInfoBuilder().title("知识文档")
                .description("文档描述了知识文档服务全面的API内容")
                .version("V1.0.0")
                .extensions(list)
                .build();
    }

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("token")
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());

        return new Docket(DocumentationType.SWAGGER_2).groupName("闫国雨")
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.acompe")) //这里写的是API接口所在的包位置
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiListingScannerPlugin getSwaggerAddtionBean(){
        return new SwaggerAddtion();
    }

    class SwaggerAddtion implements ApiListingScannerPlugin {
        @Override
        public List<ApiDescription> apply(DocumentationContext documentationContext) {
            return new ArrayList<ApiDescription>(
                    Arrays.asList(
                            new ApiDescription("闫国雨",
                                    "/oauth2/authorization/github",
                                    "获取github授权地址",
                                    Arrays.asList(
                                            new OperationBuilder(
                                                    new CachingOperationNameGenerator())
                                                    .method(HttpMethod.POST)//http请求类型
                                                    .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                                                    .summary("获取授权地址")
                                                    .notes("访问此接口，返回授权内容")//方法描述
                                                    .tags(Sets.newHashSet("用户接口"))//归类标签
                                                    .build()
                                    ),
                                    false)
                    )
            );
        }

        @Override
        public boolean supports(DocumentationType documentationType) {
            return DocumentationType.SWAGGER_2.equals(documentationType);
        }
    }

}