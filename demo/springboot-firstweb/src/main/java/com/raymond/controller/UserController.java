package com.raymond.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raymond.entity.User;
import com.raymond.service.IUserServer;
import com.raymond.util.JSONResult;
import com.raymond.util.JsonUtil;


@RestController
@RequestMapping(value = {"/user"})
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserServer userServer;
	
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;
	
	@RequestMapping(value = "/searchAll")
	public List<User> searchAll(){
		System.out.println("1111111");
		return this.userServer.searchAllUser();
	}
	
	@RequestMapping(value = "/searchMasterAll")
	public List<User> searchMasterAll(){
		System.out.println("master");
		return this.userServer.searchAllUser();
	}
	
	@RequestMapping(value = "/insertUser")
	public String insertUser(){
		System.out.println("master");
		this.userServer.insertUser();
		return "index";
	}
	
	@RequestMapping(value = "/send")
	public String send() {
		System.out.println("111111");
		this.kafkaTemplate.send("raymond-test1111", "raymond");
		JSONResult<List<User>> json = new JSONResult<>();
		json.setData(this.userServer.searchAllUser());
		this.kafkaTemplate.send("user-test",JsonUtil.toJSONString(json));
		return "index";
	}

}
