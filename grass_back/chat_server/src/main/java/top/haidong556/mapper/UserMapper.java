package top.haidong556.mapper;

import top.haidong556.entity.MyUser;

public interface UserMapper {

    public MyUser getUserById(int id);

    public void addUser(MyUser user);

}
