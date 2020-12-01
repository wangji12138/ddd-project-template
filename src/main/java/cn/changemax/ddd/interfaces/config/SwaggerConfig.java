package cn.changemax.ddd.interfaces.config;

import cn.changemax.ddd.infrastructure.properties.CmProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangJi
 * @Description swagger配置类
 * @Date 2020/12/1 14:29
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static Docket createRestApi(CmProperties properties) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("changemax v5.0 API")
                .enable(properties.getSwagger().isEnabled())
                .apiInfo(apiInfo(properties.getSwagger()))
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getSwagger().getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }


    private static ApiInfo apiInfo(CmProperties.SwaggerProperties swagger) {
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .version(swagger.getVersion())
                .contact(new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()))
                .license(swagger.getLicense())
                .licenseUrl(swagger.getLicenseUrl())
                .build();
    }

    @Bean
    public Docket restApi(CmProperties properties) {
        Docket api = createRestApi(properties);
        List<Parameter> arrayList = new ArrayList<>();
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("")
                .build();
        arrayList.add(parameter);
        api.globalOperationParameters(arrayList);
        return api;
    }

}
