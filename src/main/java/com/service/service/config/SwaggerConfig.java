package com.service.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getApiInfo())
        .select()
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo getApiInfo() {
    // 定义联系人信息
    return new ApiInfoBuilder()
        .title("演示 Swagger 的用法") // 标题
        .description("演示Swagger中各种注解的用法") // 描述信息
        .version("1.1.2") // //版本
        .license("Apache 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
        .build();
  }
}
