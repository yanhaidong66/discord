package top.haidong.oauth.service.impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import top.haidong.oauth.common.MyJedisFactory;
import top.haidong.oauth.dao.UserDao;
import top.haidong.oauth.entity.MyUser;
import top.haidong.oauth.service.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserDetailsPasswordService, UserService {
    private final String redisUserUserNameKeyPrefix="oauth:user:user_username";
    private final String redisUserIdKeyPrefix="oauth:user:user_id";
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getUserByUserName(username);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        int userId=userDao.updatePasswordByUserName(user.getUsername(),newPassword);
        return userDao.getUserById(userId);
    }

    @Override
    public MyUser getUserByUserName(String userName) {
        Jedis jedisSession= MyJedisFactory.getJedis();
        String redisResult=jedisSession.get(redisUserUserNameKeyPrefix+userName);
        if(redisResult!=null)
            return JSON.parseObject(redisResult, MyUser.class);
        return userDao.getUserByUserName(userName);
    }

    @Override
    public int addUser(MyUser user) {
        int result=userDao.addUser(user);
        Jedis jedisSession=MyJedisFactory.getJedis();
        jedisSession.set(redisUserUserNameKeyPrefix+user.getUsername(),JSON.toJSONString(user));
        jedisSession.set(redisUserIdKeyPrefix+user.getId(),JSON.toJSONString(user));
        return result;
    }
}
