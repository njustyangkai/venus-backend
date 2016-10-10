package com.venus.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.venus.dao.StudentDao;
import com.venus.pojo.StudentInfo;

@Service
public class StudentService {
	
	@Resource
	private StudentDao studentDao;
	public List<StudentInfo> getStudents() throws Exception {
		return studentDao.getStudents();
	}
}
