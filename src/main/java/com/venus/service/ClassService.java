package com.venus.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.venus.dao.ClassDao;

@Service
public class ClassService {

	@Resource
	private ClassDao classDao;

	public boolean add(String id, JSONObject addInfo) throws Exception {
		return classDao.add(id, addInfo);
	}
}
