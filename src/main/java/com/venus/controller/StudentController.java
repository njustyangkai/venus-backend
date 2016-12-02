package com.venus.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.venus.frame.utils.ResultBean;
import com.venus.service.StudentService;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/student")
public class StudentController {
	private ResultBean result;

	@Resource
	private StudentService studentService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object getStudents() {
		try {
			List<Map<String, Object>> students = studentService.getStudents();
			result = ResultBean.getSuccess("", students);
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}
}
