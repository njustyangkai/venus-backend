package com.venus.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

@Repository
public class ClassDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> get(String start, String end, String id) throws Exception {
		String sql = "select * from v_event where start_time>? and end_time<? and student_id=?";
		return jdbcTemplate.queryForList(sql, Timestamp.valueOf(start), Timestamp.valueOf(end), id);
	}

	public List<Map<String, Object>> getAll(String start, String end) {
		String sql = "select * from v_event where start_time>? and end_time<?";
		return jdbcTemplate.queryForList(sql, Timestamp.valueOf(start), Timestamp.valueOf(end));
	}

	public boolean add(String id, JSONObject addInfo) throws Exception {
		String sql = "insert into v_event(event_id,student_id,student_name,teacher_id,teacher_name,color,start_time,end_time) values(?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, id, addInfo.getString("student_id"), addInfo.getString("student_name"),
				addInfo.getString("teacher_id"), addInfo.getString("teacher_name"), addInfo.getString("color"),
				Timestamp.valueOf(addInfo.getString("start_time")),
				Timestamp.valueOf(addInfo.getString("end_time"))) > 0;
	}

	public void del(String id) throws Exception {
		String sql = "delete from v_event where event_id=?";
		jdbcTemplate.update(sql, id);
	}

	public List<Map<String, Object>> getById(String id) throws Exception {
		String sql = "select * from v_event where student_id=? order by start_time asc";
		return jdbcTemplate.queryForList(sql, id);
	}

	public void payTag(String id, JSONObject tag) throws Exception {
		String sql = "update v_event set pay=? where event_id=?";
		jdbcTemplate.update(sql, tag.getIntValue("pay"), id);
	}

	public boolean delByTime(String start, String end) throws Exception {
		String sql = "delete from v_event where start_time>? and end_time<?";
		return jdbcTemplate.update(sql, Timestamp.valueOf(start), Timestamp.valueOf(end)) > -1;
	}

	public void batchAdd(List<Map<String, Object>> addInfo) throws Exception {
		int length = addInfo.size();
		String[] sqls = new String[length];
		for (int i = 0; i < length; i++) {
			Map<String, Object> map = addInfo.get(i);
			sqls[i] = "insert into v_event(event_id,student_id,student_name,teacher_id,teacher_name,color,start_time,end_time) values('"
					+ map.get("event_id") + "','" + map.get("student_id") + "','" + map.get("student_name") + "','"
					+ map.get("teacher_id") + "','" + map.get("teacher_name") + "','" + map.get("color") + "','"
					+ map.get("start_time") + "','" + map.get("end_time") + "')";
		}
		jdbcTemplate.batchUpdate(sqls);
	}
}
