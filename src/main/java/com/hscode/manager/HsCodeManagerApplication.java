// LucasJay 17302732991
package com.hscode.manager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * HS编码管理系统启动类
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "HS编码管理系统 API",
        version = "1.0.0",
        description = "HS编码管理系统的RESTful API文档"
    )
)
public class HsCodeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsCodeManagerApplication.class, args);
    }
}
