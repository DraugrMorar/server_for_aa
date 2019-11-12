package ru.tatiana.stacy.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tatiana.stacy.Entity.Users;
import ru.tatiana.stacy.Mapper.UsersMapper;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    private UsersMapper usersMapper;

    public Controller(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/all")
    public List<Users> getInfo(){
        return usersMapper.findAll();
    }
}
