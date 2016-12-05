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
import com.venus.service.LoginService;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/login")
public class LoginController {

	private ResultBean result;

	@Resource
	private LoginService loginService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody JSONObject loginInfo) {
		try {
			Map<String, Object> res = loginService.login(loginInfo);
			if (res.get("user") != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> user = (Map<String, Object>) res.get("user");
				if ((Integer) user.get("status") == 1) {
					result = ResultBean.getSuccess("", res);
					loginService.changeLastLogTime((String) user.get("user_id"));
				} else {
					result = ResultBean.getFail("账号未激活！", null);
				}

			} else {
				result = ResultBean.getFail("账号或密码错误！", null);
			}

		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}

		return result;
	}
}
