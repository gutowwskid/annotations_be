package pl.edu.pw.mini.core.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(Collections.singletonList((new ParameterBuilder()
                        .name("X-AUTH-TOKEN")
                        .description("Token autoryzacyjny")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .defaultValue("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJJRCIsInByb2ZpbGUiOiJBTk5PVEFUT1IiLCJpcCI6IioiLCJ1c2VyTmFtZSI6IkFubm90YXRpb25zIFRlYW0iLCJleHRlcm5hbFRva2VuIjoiZTk2OThlZWM4YTI0ZjMyN2QxOWIzZTdlZTkwODg3NTg1NjE3MjViZSIsImV4cCI6MTU4ODkzMDk5MywiaXNzIjoibWluaV9wdyIsImlhdCI6MTU1NzMwODU0OSwianRpIjoiMmExZThlMDUtNDdiOC00YzI4LWE1YmEtNjYzM2FmODM5MTkzIn0.Pw8epqRfOPrYHgdIdezT-NSTQmDhJFPQD5ZXiW7HW5E")
                        .build())))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
