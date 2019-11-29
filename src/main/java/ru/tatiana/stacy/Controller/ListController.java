package ru.tatiana.stacy.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tatiana.stacy.Entity.ToDoList;
import ru.tatiana.stacy.Mapper.UsersMapper;
import ru.tatiana.stacy.Mapper.ToDoMapper;
import  java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/list")
@Api(value="List", description="All actions with to_do list")
public class ListController {

    private UsersMapper usersMapper;
    private ToDoMapper toDoMapper;

    public ListController(ToDoMapper toDoMapper, UsersMapper usersMapper) {
        this.toDoMapper = toDoMapper;
        this.usersMapper = usersMapper;
    }


//    @GetMapping("/login/{name}/{password}")
//    public List<Users> check(@PathVariable("name") String name, @PathVariable("password") String password){
//        if (!usersMapper.findByName(name).equals(password) && usersMapper.findByName(name).length() != 0)
//            return usersMapper.findU(0 + "", 0 + "");
//        return usersMapper.findU(name, password);
//    }

    @ApiOperation(value = "Add new item, identifying destination list by list_id", response = Iterable.class)
    @GetMapping("/add_by_L/{list_id}/{title}/{description}/{date}/{status}/{priority}")
    public List<ToDoList> add_L(@PathVariable("list_id") int list_id, @PathVariable("title") String title,
                              @PathVariable("description") String description, @PathVariable("date") Date date,
                              @PathVariable("status") int status, @PathVariable("priority") int priority){
        toDoMapper.addEl(list_id, title, description, date, status, priority);
        return toDoMapper.allEls(list_id);

    }

    @ApiOperation(value = "Add new item, identifying destination list by user_id", response = Iterable.class)
    @GetMapping("/add_by_U/{users_id}/{title}/{description}/{date}/{status}/{priority}")
    public List<ToDoList> add_U(@PathVariable("users_id") int users_id, @PathVariable("title") String title,
                                @PathVariable("description") String description, @PathVariable("date") Date date,
                                @PathVariable("status") int status, @PathVariable("priority") int priority){
        //if (toDoMapper.takeListId(users_id) == 0)
        toDoMapper.addEl(toDoMapper.takeListId(users_id), title, description, date, status, priority);
        return toDoMapper.allEls(toDoMapper.takeListId(users_id));
    }

    @ApiOperation(value = "Return all items from list by list_id", response = Iterable.class)
    @GetMapping("/Items/{list_id}")
    public List<ToDoList> allItems(@PathVariable("list_id") int list_id){
        return toDoMapper.allEls(list_id);
    }

    @ApiOperation(value = "Return one item from list by item_id", response = Iterable.class)
    @GetMapping("/one_item/{item_id}")
    public List<ToDoList> oneItem(@PathVariable("item_id") int item_id){
        return toDoMapper.takeByItemId(item_id);
    }

    @ApiOperation(value = "Return all items from list by user_id sorted by date", response = Iterable.class)
    @GetMapping("/Items_U/{user_id}")
    public List<ToDoList> allItemsU(@PathVariable("user_id") int user_id){
        return toDoMapper.allEls(toDoMapper.takeListId(user_id));
    }

    @ApiOperation(value = "Return all items from list by user_id sorted by status", response = Iterable.class)
    @GetMapping("/Items_Status/{user_id}")
    public List<ToDoList> allItemsStatus(@PathVariable("user_id") int user_id){
        return toDoMapper.orderStatus(toDoMapper.takeListId(user_id));
    }

    @ApiOperation(value = "Return all items from list by user_id sorted by priority", response = Iterable.class)
    @GetMapping("/Items_Priority/{user_id}")
    public List<ToDoList> allItemsPriority(@PathVariable("user_id") int user_id){
        return toDoMapper.orderPriority(toDoMapper.takeListId(user_id));
    }

    @ApiOperation(value = "Return all items from list by user_id sorted by name", response = Iterable.class)
    @GetMapping("/Items_Name/{user_id}")
    public List<ToDoList> allItemsName(@PathVariable("user_id") int user_id){
        return toDoMapper.orderName(toDoMapper.takeListId(user_id));
    }

    @ApiOperation(value = "Return all items from all lists", response = Iterable.class)
    @GetMapping("/check")
    public List<ToDoList> checks() {
        return toDoMapper.checker();
    }

    @ApiOperation(value = "Delete item from list by list_id and item_id", response = Iterable.class)
    @GetMapping("/del_item/{list_id}/{item_id}")
    public List<ToDoList> delItem(@PathVariable("list_id") int list_id, @PathVariable("item_id") int item_id){
        List<ToDoList> item = toDoMapper.takeByItemId(item_id);
       toDoMapper.delEl(list_id, item_id);
        return item;
    }

    @ApiOperation(value = "Delete hole list by list_id", response = Iterable.class)
    @GetMapping("/del_list/{list_id}")
    public List<ToDoList> delList(@PathVariable("list_id") int list_id){
        List<ToDoList> items = toDoMapper.allEls(list_id);
        toDoMapper.delTasks(list_id);
        toDoMapper.delLI(list_id);
        return items;
    }


    @ApiOperation(value = "Change item from list by item_id", response = Iterable.class)
    @GetMapping("/corr_item/{list_id}/{title}/{description}/{date}/{status}/{priority}/{item_id}")
    public List<ToDoList> corrItem(@PathVariable("list_id") int list_id, @PathVariable("title") String title,
                                   @PathVariable("description") String description, @PathVariable("date") Date date,
                                   @PathVariable("status") int status, @PathVariable("priority") int priority, @PathVariable("item_id") int item_id){
         toDoMapper.corrEl(list_id, title, description, date, status, priority, item_id);
        return toDoMapper.takeByItemId(item_id);
    }
}
