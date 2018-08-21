package cn.thislx.springbootjta.service.impl;

import cn.thislx.springbootjta.core.User;
import cn.thislx.springbootjta.service.UserPrimaryService;
import cn.thislx.springbootjta.service.UserSecondService;
import cn.thislx.springbootjta.service.dao.primary.UserPrimaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserPrimaryServiceImpl implements UserPrimaryService {
    @Resource
    private UserPrimaryMapper userPrimaryMapper;

    @Resource
    private UserSecondService userSecondService;

    @Override
    @Transactional
    public void insert(User user) {

        //测试分布式事务
        userSecondService.insert(user);
        userPrimaryMapper.insert(user);
        int i = 1 / 0;
    }
}
