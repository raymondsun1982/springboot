package com.raymond.dao;

import java.util.List;

import com.raymond.entity.User;

import tk.mybatis.mapper.common.BaseMapper;

public interface UserMapper extends BaseMapper<User>{
    public List<User> searchAll();
    
    public void inertUser();
}