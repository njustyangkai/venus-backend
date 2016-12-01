package com.venus.service;

import java.util.UUID;

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

	public boolean isUsernameUsed(JSONObject username) throws Exception {
		return userDao.isUsernameUsed(username);
	}

	public boolean addUser(JSONObject userInfo) throws Exception {
		String id = UUID.randomUUID().toString();
		boolean result = userDao.addUser(id, userInfo)
				&& userDao.addRole(id);
		if (!result)
		{
			userDao.delUser(id);
			userDao.delRole(id);
		}
		return result;
	}

}
