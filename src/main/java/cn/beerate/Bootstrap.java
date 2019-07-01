package cn.beerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class,args);
    }

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("巴雷特接口文档")
//                .description("文档描述~~")
//                .termsOfServiceUrl("NO terms of service")
//                .contact(new Contact("zhangminxiang","none","294315568@qq.com"))
//                .version("1.0.0")
//                .build();
//    }
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo()).select()
//                //扫描指定包中的swagger注解
//                .apis(RequestHandlerSelectors.basePackage("cn.beerate"))
//                //扫描所有有注解的api，用这种方式更灵活
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
}
