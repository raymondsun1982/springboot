package com.raymond.service;

import java.util.List;

import com.raymond.entity.User;

public interface IUserServer{
	
	public List<User> searchAllUser();
	
	public List<User> searchMasterAllUser();
	
	public void insertUser();
}
