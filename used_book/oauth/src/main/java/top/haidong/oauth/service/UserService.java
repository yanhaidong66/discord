package top.haidong.oauth.service;

import top.haidong.oauth.entity.MyUser;

public interface UserService {
    public MyUser getUserByUserName(String userName);
    public int addUser(MyUser user);
}
