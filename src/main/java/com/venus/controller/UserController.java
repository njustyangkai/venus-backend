package com.venus.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.venus.frame.utils.ResultBean;
import com.venus.service.UserService;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/user")
public class UserController {

	private ResultBean result;

	@Resource
	private UserService userService;

	@RequestMapping(value = "modifyPwd/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public Object modifyPwd(@PathVariable String userId, @RequestBody JSONObject newPwd) {
		try {
			if (userService.modifyPwd(userId, newPwd)) {
				result = ResultBean.getSuccess("", null);
			} else {
				result = ResultBean.getFail("", null);
			}
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "isPwdRight", method = RequestMethod.POST)
	@ResponseBody
	public Object isPwdRight(@RequestBody JSONObject pwd) {
		try {
			if (userService.isPwdRight(pwd)) {
				result = ResultBean.getSuccess("", null);
			} else {
				result = ResultBean.getFail("", null);
			}
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "isUsernameUsed", method = RequestMethod.POST)
	@ResponseBody
	public Object isUsernameUsed(@RequestBody JSONObject username) {
		try {
			result = ResultBean.getSuccess("", userService.isUsernameUsed(username));

		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(@RequestBody JSONObject userInfo) {
		try {
			String id = UUID.randomUUID().toString();
			if (userService.addUser(id, userInfo)) {
				result = ResultBean.getSuccess("", null);
			}
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

}
