package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    /**
     * 通过userid查询用户信息
     * @param openid
     * @return
     */
    User getByOpenid(String openid);

    /**
     * 插入User对象
     * @param user
     */
    void insert(User user);
}
