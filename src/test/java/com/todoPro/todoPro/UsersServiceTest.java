package com.todoPro.todoPro;

;
import com.todoPro.todoPro.Mapper.UsersMapper;
import com.todoPro.todoPro.service.UsersService;
import com.todoPro.todoPro.vo.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsersServiceTest {

    @Mock
    private UsersMapper usersMapper;

    @InjectMocks
    private UsersService usersService;

    private Users testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new Users();
        testUser.setUserId("test");
        testUser.setPw("test");
    }

    @Test
    void testSignup() {
        doNothing().when(usersMapper).signup(testUser);

        assertDoesNotThrow(() -> usersService.signup(testUser));
        verify(usersMapper, times(1)).signup(testUser);
    }

    @Test
    void testGetUsers() throws Exception {
        when(usersMapper.getUsers(testUser)).thenReturn(testUser);

        Users result = usersService.findUsers(testUser.getUserId());

        assertNotNull(result);
        assertEquals("test", result.getUserId());
        verify(usersMapper, times(1)).findUsers(testUser.getUserId());
    }

    @Test
    void testUpdateUser() {
        when(usersMapper.updateUser(testUser)).thenReturn(1);

        int result = usersService.updateUser(testUser);

        assertEquals(1, result);
        verify(usersMapper).updateUser(testUser);
    }

    @Test
    void testDeleteUser() {
        when(usersMapper.deleteUser(testUser)).thenReturn(1);

        int result = usersService.deleteUser(testUser);

        assertEquals(1, result);
        verify(usersMapper).deleteUser(testUser);
    }
}