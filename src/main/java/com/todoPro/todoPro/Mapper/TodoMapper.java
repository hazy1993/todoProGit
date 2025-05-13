package com.todoPro.todoPro.Mapper;

import com.todoPro.todoPro.vo.Todos;
import com.todoPro.todoPro.vo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TodoMapper {

    void createTodos(Todos todos);

    List<Todos> getAllTodos();

    Todos getTodosById(long id);

    int updateTodo(Todos todo);

    int deleteTodos(long id);

    List<Todos> searchTodos(Todos todos);

}
