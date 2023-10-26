package top.haidong.oauth.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.haidong.oauth.common.MySqlSessionFactory;
import top.haidong.oauth.entity.MyUser;
import top.haidong.oauth.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDao(){
        sqlSessionFactory= MySqlSessionFactory.getFactory();
    }
    public MyUser getUserByUserName(String userName){
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        MyUser user= sqlSession.getMapper(UserMapper.class).getUserByUserName(userName);
        sqlSession.commit();
        sqlSession.close();
        return user;
    };
    public int addUser(MyUser user){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int result=sqlSession.getMapper(UserMapper.class).addUser(user);
        sqlSession.commit();
        sqlSession.close();
        return result;
    };

    public int deleteUser(String userName){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int result=sqlSession.getMapper(UserMapper.class).deleteUser(userName);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    public int updatePasswordByUserName(String userName,String password){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int result=sqlSession.getMapper(UserMapper.class).updatePasswordByUserName(userName,password);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }
    public MyUser getUserById(int id){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        MyUser user=sqlSession.getMapper(UserMapper.class).getUserById(id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }
}
