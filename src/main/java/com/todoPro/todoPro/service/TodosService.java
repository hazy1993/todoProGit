package com.todoPro.todoPro.service;

import com.todoPro.todoPro.Mapper.TodoMapper;
import com.todoPro.todoPro.vo.Todos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodosService {

    TodoMapper todoMapper;

    public TodosService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public void createTodos(Todos todos){
         todoMapper.createTodos(todos);
    };

    public List<Todos> getAllTodos(){
        return todoMapper.getAllTodos();
    };

    public Todos getTodosById(long id){
        return todoMapper.getTodosById(id);
    };

    public int updateTodo(Todos todo){
        return todoMapper.updateTodo(todo);
    };

    public int deleteTodos(long id){
        return todoMapper.deleteTodos(id);
    };

    public List<Todos> searchTodos(Todos todos){
        return todoMapper.searchTodos(todos);
    }
}
