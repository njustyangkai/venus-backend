package com.venus.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/${application.version}/login")
public class LoginController {
	@RequestMapping(value="",method=RequestMethod.GET)
	@ResponseBody
	public Object login() {
		return "login";
	}
}
