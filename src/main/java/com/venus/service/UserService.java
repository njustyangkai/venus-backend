package com.venus.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.venus.dao.UserDao;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	public boolean modifyPwd(String userId, JSONObject newPwd) throws Exception {
		return userDao.modifyPwd(userId, newPwd);
	}

	public boolean isPwdRight(JSONObject pwd) throws Exception {
		return userDao.isPwdRight(pwd);
	}

}
