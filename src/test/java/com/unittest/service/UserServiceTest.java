package com.unittest.service;


import com.unittest.dao.UserRepository;
import com.unittest.model.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends TestCase {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;


    @Test
    public void testAddUser() {
        User user = new User(999, "Pranya", 33, "Pune","a@gmail.com");
        when(repository.save(user)).thenReturn(user);
        assertEquals(user, service.addUser(user));
    }

    @Test
    public void testGetUsers() {
        when(repository.findAll()).thenReturn(Stream
                .of(new User(376, "Danile", 31, "USA","a@gmail.com"), new User(958, "Huy", 35, "UK","a@gmail.com")).collect(Collectors.toList()));
        assertEquals(2, service.getUsers().size());
    }

    @Test
    public void testGetUserbyAddress() {
        String address = "Bangalore";
        when(repository.findByAddress(address))
                .thenReturn(Stream.of(new User(376, "Danile", 31, "USA","a@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, service.getUserbyAddress(address).size());
    }

    @Test
    public void testDeleteUser() {
        User user = new User(999, "Pranya", 33, "Pune","a@gmail.com");
        service.deleteUser(user);
        verify(repository, times(1)).delete(user);
    }


}