package com.venus.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.venus.dao.ClassDao;

@Service
public class ClassService {

	@Resource
	private ClassDao classDao;

	public List<Map<String, Object>> get(String start, String end, String id) throws Exception {
		return classDao.get(start, end, id);
	}

	public List<Map<String, Object>> getAll(String start, String end) throws Exception {
		return classDao.getAll(start, end);
	}

	public boolean add(String id, JSONObject addInfo) throws Exception {
		return classDao.add(id, addInfo);
	}

	public void del(String id) throws Exception {
		classDao.del(id);
	}
}
