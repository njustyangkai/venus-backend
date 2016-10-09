package com.venus.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.venus.dao.LoginDao;
import com.venus.pojo.User;

@Service
public class LoginService {

	@Resource 
	private LoginDao loginDao;
	
	public Map<String,Object> login(JSONObject jsonObject) throws Exception {
		Map<String, Object> logInfo = new HashMap<String, Object>();
		User user = loginDao.login(jsonObject);
		if (user != null) {
			logInfo.put("user", user);
			logInfo.put("role", loginDao.getRole(user.getId()));
		}
		return logInfo;
	}
	
	public void changeLastLogTime (String userId) throws Exception {
		loginDao.changeLastLogTime(userId);
	}
}
