package com.venus.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.venus.dao.StudentDao;

@Service
public class StudentService {

	@Resource
	private StudentDao studentDao;

	public List<Map<String, Object>> getStudents() throws Exception {
		return studentDao.getStudents();
	}
}
