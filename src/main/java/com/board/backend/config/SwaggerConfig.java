package com.board.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스웨거 설정 파일 URL : http://localhost:8082/swagger-ui/index.html
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder().group("v1-definition").pathsToMatch("/api/**").build();
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("게시판 API").description("게시판 API 명세서입니다.").version("v0.0.1"));
  }
}
