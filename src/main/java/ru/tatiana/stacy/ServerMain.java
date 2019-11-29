package ru.tatiana.stacy;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tatiana.stacy.Entity.Users;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MappedTypes(Users.class)
@MapperScan("ru.tatiana.stacy.mapper")
@SpringBootApplication
@EnableSwagger2
public class ServerMain {
    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }
}
