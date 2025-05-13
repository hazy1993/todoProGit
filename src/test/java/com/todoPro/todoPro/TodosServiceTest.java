package com.todoPro.todoPro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.todoPro.todoPro.Mapper.TodoMapper;
import com.todoPro.todoPro.service.TodosService;
import com.todoPro.todoPro.vo.Todos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TodosServiceTest {

    @Mock
    private TodoMapper todoMapper;

    @InjectMocks
    private TodosService todosService;

    private Todos testTodo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testTodo = new Todos();
        testTodo.setId(1L);
        testTodo.setTitle("테스트 제목");
        testTodo.setContent("테스트 내용");
        testTodo.setRegId("user123");
    }

    @Test
    void testCreateTodos() {
        doNothing().when(todoMapper).createTodos(testTodo);

        todosService.createTodos(testTodo);

        verify(todoMapper, times(1)).createTodos(testTodo);
    }

    @Test
    void testGetAllTodos() {
        List<Todos> expected = Arrays.asList(testTodo);
        when(todoMapper.getAllTodos()).thenReturn(expected);

        List<Todos> result = todosService.getAllTodos();

        assertEquals(1, result.size());
        assertEquals("테스트 제목", result.get(0).getTitle());
        verify(todoMapper, times(1)).getAllTodos();
    }

    @Test
    void testGetTodosById() {
        when(todoMapper.getTodosById(1L)).thenReturn(testTodo);

        Todos result = todosService.getTodosById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(todoMapper, times(1)).getTodosById(1L);
    }

    @Test
    void testUpdateTodo() {
        when(todoMapper.updateTodo(testTodo)).thenReturn(1);

        int result = todosService.updateTodo(testTodo);

        assertEquals(1, result);
        verify(todoMapper).updateTodo(testTodo);
    }

    @Test
    void testDeleteTodos() {
        when(todoMapper.deleteTodos(1L)).thenReturn(1);

        int result = todosService.deleteTodos(1L);

        assertEquals(1, result);
        verify(todoMapper).deleteTodos(1L);
    }

    @Test
    void testSearchTodos() {
        List<Todos> expected = Arrays.asList(testTodo);
        when(todoMapper.searchTodos(testTodo)).thenReturn(expected);

        List<Todos> result = todosService.searchTodos(testTodo);

        assertEquals(1, result.size());
        verify(todoMapper).searchTodos(testTodo);
    }
}