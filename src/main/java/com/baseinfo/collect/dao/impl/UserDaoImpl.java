package com.baseinfo.collect.dao.impl;

import com.baseinfo.collect.beans.UserBean;
import com.baseinfo.collect.dao.UserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * User的实现类
 */
public class UserDaoImpl implements UserDao{

    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory factory;

    @Override
    public int insert(UserBean user) throws Exception {


        return 0;
    }

    @Override
    public int update(UserBean user) throws Exception {
        return 0;
    }

    @Override
    public UserBean select(UserBean user) throws Exception {
        return null;
    }

    @Override
    public int delete(long id) throws Exception {
        return 0;
    }

    @Override
    public UserBean selectUserByUnameAndPwd(String uname, long id) throws Exception {
        return null;
    }
}
