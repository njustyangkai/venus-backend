package com.venus.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.venus.frame.utils.ResultBean;
import com.venus.pojo.User;
import com.venus.service.LoginService;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/${application.version}/login")
public class LoginController {
	
	private ResultBean result;
	
	@Resource
	private LoginService loginService;
	
	/**
	 * login
	 * 
	 * @param loginInfo {name: '',password: ''}
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody JSONObject loginInfo) {
		try {
			Map<String,Object> res = loginService.login(loginInfo);
			if (res.get("user") != null) {
				User user = (User) res.get("user");
				if (user.getState().intValue() == 1) {
					result = ResultBean.getResult(true, "", res);
					loginService.changeLastLogTime(user.getId());
				} else {
					result = ResultBean.getResult(false, "账号未激活！", null);
				}
				
			} else {
				result = ResultBean.getResult(false, "账号或密码错误！", null);
			}
			
		} catch (Exception e) {
			result = ResultBean.getResult(false, e.getMessage(), null);
		}
		
		return result;
	}
}
