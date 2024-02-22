package com.avgkin.tacocloudplusserver.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId
    public Long id;
    public String username;
    public String password;
}
