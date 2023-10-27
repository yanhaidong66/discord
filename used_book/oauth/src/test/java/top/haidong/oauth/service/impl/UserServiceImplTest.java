package top.haidong.oauth.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import top.haidong.oauth.dao.UserDao;
import top.haidong.oauth.entity.MyUser;
import top.haidong.oauth.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(
        classes = { UserServiceImpl.class,UserDao.class, UserMapper.class}
)
class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;

    @Test

    void loadUserByUsername() {
        UserDetails userDetails = userService.loadUserByUsername("haidong");
        System.out.println(userDetails.toString());

    }

    @Test
    void updatePassword() {
    }

    @Test
    void getUserByUserName() {
    }

    @Test
    void addUser() {
        MyUser user=new MyUser("haidong","555");
        userService.addUser(user);
    }
}