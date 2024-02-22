package com.avgkin.tacocloudplusserver.service.impl;

import com.avgkin.tacocloudplusserver.entity.po.User;
import com.avgkin.tacocloudplusserver.entity.po.mappers.UserMapper;
import com.avgkin.tacocloudplusserver.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User userDb = getOne(wrapper);
        return userDb;
    }
}
