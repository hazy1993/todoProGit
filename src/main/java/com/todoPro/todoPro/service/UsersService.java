package com.todoPro.todoPro.service;

import com.todoPro.todoPro.Mapper.UsersMapper;
import com.todoPro.todoPro.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersMapper usersMapper;

    public UsersService(UsersMapper UsersMapper) {
        this.usersMapper = UsersMapper;
    }

     public int signup(Users users){
        return usersMapper.signup(users);
    }

    public Users getUsers(Users users){
        return usersMapper.getUsers(users);
    }

    public Users findUsers(String users) throws Exception {
        Users Users = usersMapper.findUsers(users);
        if(Users ==null){
            throw new UsernameNotFoundException("User not found in database");
        }
        return Users;
    }

    public int updateUser(Users users){
        return usersMapper.updateUser(users);
    }

    public int deleteUser(Users users){
        return usersMapper.deleteUser(users);
    }


}
