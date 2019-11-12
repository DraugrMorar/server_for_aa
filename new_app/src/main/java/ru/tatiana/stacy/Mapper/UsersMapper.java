package ru.tatiana.stacy.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.tatiana.stacy.Entity.Users;

import java.util.List;
@Mapper
public interface UsersMapper {
    @Select("select * from users")
    List<Users> findAll();
}
/Users/OUT-Radnaeva-TZ/IdeaProjects/new_app/src/main/java/ru/tatiana/stacy/Mapper/UsersMapper.java