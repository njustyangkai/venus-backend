package com.venus.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

	public List<Map<String, Object>> getById(String id) throws Exception {
		return classDao.getById(id);
	}

	public void payTag(String id, JSONObject tag) throws Exception {
		classDao.payTag(id, tag);
	}

	public void copy(JSONObject copyInfo) throws Exception {
		if (classDao.delByTime(copyInfo.getString("start"), copyInfo.getString("end"))) {
			List<Map<String, Object>> list = classDao.getAll(copyInfo.getString("oriStart"), "oriEnd");
			for (Map<String, Object> map : list) {
				map.put("event_id", UUID.randomUUID().toString());
				Timestamp t = (Timestamp) map.get("start_time");
				t.setTime(t.getTime() + 7 * 24 * 3600 * 1000);
				Timestamp t2 = (Timestamp) map.get("end_time");
				t2.setTime(t2.getTime() + 7 * 24 * 3600 * 1000);
				map.put("start_time", t);
				map.put("end_time", t2);
			}
			classDao.batchAdd(list);
		}
	}
}
