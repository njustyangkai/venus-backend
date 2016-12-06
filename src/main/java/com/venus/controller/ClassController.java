package com.venus.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.venus.frame.utils.ResultBean;
import com.venus.service.ClassService;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/class")
public class ClassController {
	private ResultBean result;

	@Resource
	private ClassService classService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@RequestBody JSONObject addInfo) {
		try {
			String id = UUID.randomUUID().toString();
			if (classService.add(id, addInfo)) {
				result = ResultBean.getSuccess("", id);
			} else {
				result = ResultBean.getFail("", null);
			}
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}
}
