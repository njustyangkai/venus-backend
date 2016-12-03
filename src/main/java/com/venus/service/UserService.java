package com.venus.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.venus.dao.UserDao;
import com.venus.frame.constants.Constants;

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

	public boolean addUser(String id, JSONObject userInfo) throws Exception {
		boolean result = userDao.addUser(id, userInfo) && userDao.addRole(id, Constants.AUTH_TYPE_STUDENT);
		if (!result) {
			userDao.delUser(id);
			userDao.delRole(id);
		}
		return result;
	}

	public void delUser(String id) throws Exception {
		userDao.delUser(id);
	}

	public void delRole(String id) throws Exception {
		userDao.delRole(id);
	}

}
