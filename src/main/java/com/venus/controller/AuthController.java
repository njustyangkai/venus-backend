package com.venus.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@RequestMapping("auth")
public class AuthController {
	@RequestMapping(value="loadStudents",method=RequestMethod.GET )
	@ResponseBody
	public Object loadStudents() {
		return "this is a test";
	}
}
