package com.unittest.dao;

import com.unittest.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository useRepository;

    // JUnit test f
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest(){

        User user = User.builder()
                .firstName("Ramesh")
                .address("galle")
                .email("ramesh@gmail.com")
                .build();

        useRepository.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUserTest(){
        User user = useRepository.findById(1).get();
        Assertions.assertThat(user.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfUserTest(){
        List<User> user = useRepository.findAll();
        Assertions.assertThat(user.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUserTest(){
        User user = useRepository.findById(1).get();
        user.setEmail("ram@gmail.com");
        User UserUpdated =  useRepository.save(user);
        Assertions.assertThat(UserUpdated.getEmail()).isEqualTo("ram@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUserTest(){

        User user = useRepository.findById(1).get();
        useRepository.delete(user);
        //useRepository.deleteById(1L);
        User User1 = null;
        Optional<User> optionalUser = useRepository.findByEmail("ram@gmail.com");
        if(optionalUser.isPresent()){
            User1 = optionalUser.get();
        }
        Assertions.assertThat(User1).isNull();
    }

}
