package ru.tatiana.stacy.Mapper;

import org.apache.ibatis.annotations.*;
import ru.tatiana.stacy.Entity.ToDo;
import ru.tatiana.stacy.Entity.ToDoList;

import java.util.Date;
import java.util.List;

@Mapper

public interface ToDoMapper {
    @Insert("insert into list (list_id, title, description, date, status, priority) values(#{list_id}, #{title}," +
            " #{description}, #{date}, #{status}, #{priority})")
    void addEl(@Param("list_id") int list_id, @Param("title") String title, @Param("description") String description,
                         @Param("date") Date date, @Param("status") int status, @Param("priority") int priority);
    @Select("select item_id from list where list_id = #{list_id} and title=#{title} and description=#{description} and" +
            " date=#{date} and status = #{status} and priority=#{priority}")
        int takeItemId(@Param("list_id") int list_id, @Param("title") String title, @Param("description") String description,
                       @Param("date") Date date, @Param("status") int status, @Param("priority") int priority);
    @Select("select * from list where item_id = #{item_id}")
        List<ToDoList>takeByItemId(@Param("item_id") int item_id);
    @Select("select list_id from to_do where users_id = #{users_id}")
        int takeListId(@Param("users_id") int users_id);
    @Delete("delete from list where list_id = #{list_id} and item_id = #{item_id}")
        void delEl(@Param("list_id") int list_id, @Param("item_id") int item_id);
    @Delete("delete from list where list_id = #{list_id}")
        void delTasks(@Param("list_id") int list_id);
    @Delete("delete from to_do where list_id = #{list_id}")
    void delLI(@Param("list_id") int list_id);
    @Select("select * from list where list_id = #{list_id} order by date, status, priority desc, title")
        List<ToDoList> allEls(@Param("list_id") int list_id);
    @Select("select * from list where list_id = #{list_id} order by status, date, priority desc, title")
        List<ToDoList> orderStatus(@Param("list_id") int list_id);
    @Select("select * from list where list_id = #{list_id} order by priority desc, date, status, title")
    List<ToDoList> orderPriority(@Param("list_id") int list_id);
    @Select("select * from list where list_id = #{list_id} order by title, date, status, priority desc")
    List<ToDoList> orderName(@Param("list_id") int list_id);
    @Select("select * from list order by list_id, date, status desc, priority")
    List<ToDoList> checker();
    @Update("update list set list_id=#{list_id}, title=#{title}, description=#{description}, date=#{date}, " +
            "status = #{status}, priority=#{priority} where item_id=#{item_id}")
        void corrEl(@Param("list_id") int list_id, @Param("title") String title, @Param("description") String description,
                              @Param("date") Date date, @Param("status") int status, @Param("priority") int priority, @Param("item_id") int item_id);
}
