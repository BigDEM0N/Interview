package com.avgkin.tacocloudplusserver.service;

import com.avgkin.tacocloudplusserver.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User getByUsername(String username);
}
