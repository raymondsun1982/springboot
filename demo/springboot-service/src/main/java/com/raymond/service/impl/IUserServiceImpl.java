package com.raymond.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raymond.config.ReadOnly;
import com.raymond.dao.UserMapper;
import com.raymond.entity.User;
import com.raymond.service.IUserServer;

@Service
public class IUserServiceImpl implements IUserServer{

	@Autowired
	private UserMapper userMapper;

	@Override
	@ReadOnly
	public List<User> searchAllUser() {
		return this.userMapper.searchAll();
	}


	@Override
	public void insertUser() {
		User user = new User();
		user.setUsername("raymond");
		user.setPassword("raymond");
		this.userMapper.insert(user);
	}


	@Override
	public List<User> searchMasterAllUser() {
		return this.userMapper.searchAll();
	}

}
