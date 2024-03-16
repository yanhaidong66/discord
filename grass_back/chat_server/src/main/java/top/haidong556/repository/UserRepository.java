package top.haidong556.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.common.sqlsession.MySqlSessionFactory;
import top.haidong556.entity.MyUser;
import top.haidong556.mapper.UserMapper;


public class UserRepository {
    private MySqlSessionFactory sqlSessionFactory;
    private static UserRepository instance;
    static {
        instance=new UserRepository();
        instance.sqlSessionFactory=MySqlSessionFactory.getInstance();
    }

    private UserRepository(){}
    public static UserRepository getInstance(){
        return instance;
    }

    public MyUser getUserById(int id){
        SqlSession sqlSession= sqlSessionFactory.getSession(false);
        MyUser user= sqlSession.getMapper(UserMapper.class).getUserById(id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }
    public boolean addUser(MyUser user){
        SqlSession sqlSession=sqlSessionFactory.getSession(false);
        sqlSession.getMapper(UserMapper.class).addUser(user);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
