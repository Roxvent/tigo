package cl.intelidata.appwhatsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalParameterList())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cl.intelidata.appwhatsapp"))
                .build();
    }

    private List<Parameter> globalParameterList() {
        List<Parameter> parameterList = new ArrayList<>();
        return parameterList;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Intelidata - API Whatsapp Chattigo",
                "Api para gestión de mensajes por WhatsApp.",
                "1.0.3",
                "",
                new Contact("Intelidata", "https://intelidata.cl", ""),
                "", "", Collections.emptyList());
    }

}
