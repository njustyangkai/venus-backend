package com.venus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.venus.service.StudentService;
import com.venus.service.UserService;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/student")
public class StudentController {
	private ResultBean result;

	@Resource
	private StudentService studentService;
	@Resource
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get() {
		try {
			List<Map<String, Object>> students = studentService.get();
			result = ResultBean.getSuccess("", students);
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@RequestBody JSONObject addInfo) {
		String id = UUID.randomUUID().toString();
		try {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("username", addInfo.getString("username"));
			userMap.put("password", addInfo.getString("password"));
			if (userService.addUser(id, new JSONObject(userMap))) {
				if (studentService.add(id, addInfo)) {
					result = ResultBean.getSuccess("", null);
				} else {
					result = ResultBean.getFail("", null);
				}
			} else {
				result = ResultBean.getFail("", null);
			}
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object del(@PathVariable String id) {
		try {
			userService.delUser(id);
			userService.delRole(id);
			studentService.del(id);
			result = ResultBean.getSuccess("", null);
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Object edit(@PathVariable String id, @RequestBody JSONObject editInfo) {
		try {
			studentService.edit(id, editInfo);
			result = ResultBean.getSuccess("", null);
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@PathVariable String id) {
		try {
			result = ResultBean.getSuccess("", studentService.getById(id));
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object addInfo(@PathVariable String id, @RequestBody JSONObject info) {
		try {
			if (studentService.add(id, info)) {
				result = ResultBean.getSuccess("", null);
			} else {
				result = ResultBean.getFail("", null);
			}
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "forPicker", method = RequestMethod.GET)
	@ResponseBody
	public Object getForPicker() {
		try {
			result = ResultBean.getSuccess("", studentService.getForPicker());

		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

}
