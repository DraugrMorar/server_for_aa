package ru.tatiana.stacy.Mapper;

import org.apache.ibatis.annotations.*;
import ru.tatiana.stacy.Entity.Users;

import java.util.List;
@Mapper
public interface UsersMapper {
    @Select("select * from users order by users_id")
    List<Users> findAll();
    @Select("select password from users where name = #{name}")
    String findByName(@Param("name") String name);
    @Select("SELECT * FROM users WHERE name = #{name} AND password = #{password}")
    List<Users> findU(@Param("name") String name, @Param("password") String password);
    @Select("SELECT users_id FROM users WHERE name = #{name} AND password = #{password}")
    Integer findUser(@Param("name") String name, @Param("password") String password);
    @Select("SELECT * FROM users WHERE users_id=#{id}")
    List<Users> findById(@Param("id") int id);
    @Delete("delete from users where users_id=#{id}")
    void delete(@Param("id") int id);
    @Insert("insert into users (name, password) values (#{name}, #{password})")
    void add(@Param("name") String name, @Param("password") String password);
    @Insert("insert into to_do (acc_id, users_id) values (#{acc_id}, #{users_id})")
    void newLI(@Param("acc_id") int acc_id, @Param("users_id") int users_id);
    @Update("update users set password=#{new_password} where name = #{name} and password = #{password}")
    void new_passw(@Param("name") String name, @Param("password") String password,
                          @Param("new_password") String new_password);
}
