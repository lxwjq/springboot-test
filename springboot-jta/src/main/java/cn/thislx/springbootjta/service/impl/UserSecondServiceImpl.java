package cn.thislx.springbootjta.service.impl;

import cn.thislx.springbootjta.core.User;
import cn.thislx.springbootjta.service.UserSecondService;
import cn.thislx.springbootjta.service.dao.second.UserSecondMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserSecondServiceImpl implements UserSecondService {
    @Resource
    private UserSecondMapper userSecondMapper;

    @Override
    @Transactional
    public void insert(User user) {
        userSecondMapper.insert(user);
    }
}
