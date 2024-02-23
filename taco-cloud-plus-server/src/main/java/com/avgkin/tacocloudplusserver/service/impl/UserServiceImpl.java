package com.avgkin.tacocloudplusserver.service.impl;

import cn.hutool.core.util.IdUtil;
import com.avgkin.tacocloudplusserver.entity.dto.RegistrationRequestDTO;
import com.avgkin.tacocloudplusserver.entity.po.User;
import com.avgkin.tacocloudplusserver.entity.po.mappers.UserMapper;
import com.avgkin.tacocloudplusserver.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User userDb = getOne(wrapper);
        return userDb;
    }

    @Override
    public User registerUser(RegistrationRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = new User(IdUtil.getSnowflakeNextId(),requestDTO.getUsername(),passwordEncoder.encode(requestDTO.getPassword()));
        if(exists(wrapper)){
            return null;
        }else{
            save(user);
        }
        return user;
    }
}
