package ru.tatiana.stacy.Controller;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tatiana.stacy.Entity.Users;
import ru.tatiana.stacy.Entity.ToDoList;
import ru.tatiana.stacy.Entity.ToDo;
import ru.tatiana.stacy.Mapper.UsersMapper;
import ru.tatiana.stacy.Mapper.ToDoMapper;
import  java.sql.Date;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/users")
@Api(value="Users", description="All actions with user's accounts")

public class UsersController {
    private UsersMapper usersMapper;
    private ToDoMapper toDoMapper;

    public UsersController(UsersMapper usersMapper, ToDoMapper toDoMapper) {
        this.usersMapper = usersMapper;
        this.toDoMapper = toDoMapper;
    }

    @ApiOperation(value = "test request", response = Iterable.class)
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation(value = "Return all users", response = Iterable.class)
    @GetMapping("/all")
    public List<Users> getInfo(){LoggerFactory.getLogger("").info("GET ALL");
        return usersMapper.findAll();
    }

    @ApiOperation(value = "Delete user by user_id", response = Iterable.class)
    @GetMapping("/delete/{id}")
    public List<Users> deleteUser(@PathVariable("id") int id){
        LoggerFactory.getLogger("").info("in delete");
        List<Users> ret = usersMapper.findById(id);
        LoggerFactory.getLogger("").info("users found: " + ret.size());
        usersMapper.delete(id);
        return (ret);
    }

    @ApiOperation(value = "Check name and password, return user's data", response = Iterable.class)
    @GetMapping("/login/{name}/{password}")
    public Users Userscheck(@PathVariable("name") String name, @PathVariable("password") String password){
        List<Users> list;
        if (usersMapper.findByName(name) == null)
        {
            list = usersMapper.findU("0", "0");
        }
        else if (!usersMapper.findByName(name).equals(password))
            list = usersMapper.findU("0", "0");
        else
            list = usersMapper.findU(name, password);
        return list.get(0);
    }

    @ApiOperation(value = "Add new user (sign up)", response = Iterable.class)
    @GetMapping("/add/{name}/{password}")
    public Users add( @PathVariable("name") String name, @PathVariable("password") String password){
        List<Users> list;
        if (usersMapper.findByName(name) != null)
            list = usersMapper.findU("0", "0");
        else {
            usersMapper.add( name, password);
            list = usersMapper.findU(name, password);
            usersMapper.newLI(1, usersMapper.findUser(name, password));
        }
        return list.get(0);
    }

    @ApiOperation(value = "Change user's password", response = Iterable.class)
    @GetMapping("/change/{name}/{password}/{new_password}")
    public List<Users> change( @PathVariable("name") String name, @PathVariable("password") String password,
                               @PathVariable("new_password") String new_password){
        usersMapper.new_passw(name, password, new_password);
        return usersMapper.findU(name, new_password);
    }
    @ApiOperation(value = "Return password by name", response = Iterable.class)
    @GetMapping("/take_pass/{name}")
    public String takePass(@PathVariable("name") String name){
        LoggerFactory.getLogger("").info("take_password");
        return usersMapper.findByName(name);
    }
    @ApiOperation(value = "Return list_id using users_id", response = Iterable.class)
    @GetMapping("/take_LI/{users_id}")
    public int take_LI(@PathVariable("users_id") int users_id){
        return toDoMapper.takeListId(users_id);
    }
}
