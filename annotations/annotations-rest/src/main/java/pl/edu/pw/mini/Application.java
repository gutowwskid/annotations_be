package pl.edu.pw.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {BasePackageIndicator.class})
@EntityScan(basePackageClasses = BasePackageIndicator.class)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(pl.edu.pw.mini.core.Application.class, args);
    }
}
