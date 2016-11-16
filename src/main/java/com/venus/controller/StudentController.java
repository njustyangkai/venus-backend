package com.venus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.venus.frame.utils.ResultBean;
import com.venus.pojo.StudentInfo;
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
			List<StudentInfo> students = new ArrayList<StudentInfo>();
			students = studentService.getStudents();
			result = ResultBean.getResult(true, "", students);
		} catch (Exception e) {
			result = ResultBean.getResult(false, e.getMessage(), null);
		}

		return result;

	}
}
