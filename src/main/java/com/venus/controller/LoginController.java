package com.venus.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.venus.frame.utils.ResultBean;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/${application.version}/login")
public class LoginController {
	
	private ResultBean result;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody Object loginInfo) {
		System.out.println(loginInfo);
		result = ResultBean.getResult(true, "", loginInfo);
		return result;
	}
}
