package com.todoPro.todoPro.Mapper;

import com.todoPro.todoPro.vo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Mapper
public interface UsersMapper {

    int signup(Users users);

    Users getUsers(Users users);

    Users findUsers(String usersId);

    int updateUser(Users users);

    int deleteUser(Users users);

}
