package com.avgkin.tacocloudplusserver.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId
    public Long id;
    public String username;
    public String password;
}
