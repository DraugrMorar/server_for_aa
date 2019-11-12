package ru.tatiana.stacy;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tatiana.stacy.Entity.Users;

@MappedTypes(Users.class)
@MapperScan("ru.tatiana.stacy.mapper")
@SpringBootApplication
public class ServerMain {
    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }
}
