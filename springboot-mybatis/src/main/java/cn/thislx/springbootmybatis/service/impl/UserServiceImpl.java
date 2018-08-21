package cn.thislx.springbootmybatis.service.impl;

import cn.thislx.springbootmybatis.core.User;
import cn.thislx.springbootmybatis.mapper.UserMapper;
import cn.thislx.springbootmybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: LX
 * @Description:
 * @Date: Created in 15:20 2018/8/20
 * @Modified by:
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
