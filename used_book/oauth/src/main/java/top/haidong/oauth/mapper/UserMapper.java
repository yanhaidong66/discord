package top.haidong.oauth.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.haidong.oauth.entity.MyUser;

@Mapper
public interface UserMapper {
    public MyUser getUserByUserName(String userName);
    public MyUser getUserById(int id);
    public int addUser(MyUser user);
    public int deleteUser(String userName);
    public int updatePasswordByUserName(String userName,String password);
}
