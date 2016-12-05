package com.venus.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.venus.dao.StudentDao;

@Service
public class StudentService {

	@Resource
	private StudentDao studentDao;

	public List<Map<String, Object>> get() throws Exception {
		return studentDao.get();
	}

	public boolean add(String id, JSONObject addInfo) throws Exception {
		boolean res = studentDao.add(id, addInfo);
		if (!res) {
			studentDao.del(id);
		}
		return res;
	}

	public void del(String id) throws Exception {
		studentDao.del(id);
	}

	public void edit(String id, JSONObject editInfo) throws Exception {
		studentDao.edit(id, editInfo);
	}

	public Map<String, Object> getById(String id) throws Exception {
		return studentDao.getById(id);
	}
}
