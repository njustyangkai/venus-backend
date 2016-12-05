package com.venus.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

@Repository
public class StudentDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> get() throws Exception {
		String sql = "select u.*, a.auth_type, s.* from v_user u left join v_student s on u.user_id=s.student_id,"
				+ " v_user_auth a where u.user_id = a.user_id and a.auth_type = 1 order by u.username asc";
		return jdbcTemplate.queryForList(sql);
	}

	public boolean add(String id, JSONObject addInfo) throws Exception {
		String sql = "insert into v_student(student_id,name,email,grade,birthday,phone,parent_phone,sex) values(?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, id, addInfo.getString("name"), addInfo.getString("email"),
				addInfo.getString("grade"), addInfo.getDate("birthday"), addInfo.getString("phone"),
				addInfo.getString("parentPhone"), addInfo.getString("sex")) > 0;
	}

	public void del(String id) throws Exception {
		String sql = "delete from v_student where student_id=?";
		jdbcTemplate.update(sql, id);
	}

	public void edit(String id, JSONObject editInfo) throws Exception {
		String sql = "update v_student set name=?, email=?, grade=?, birthday=?, phone=?, parent_phone=?,sex=? where student_id=?";
		jdbcTemplate.update(sql, editInfo.getString("name"), editInfo.getString("email"), editInfo.getString("grade"),
				editInfo.getDate("birthday"), editInfo.getString("phone"), editInfo.getString("parent_phone"),
				editInfo.getString("sex"), id);
	}

	public Map<String, Object> getById(String id) throws Exception {
		String sql = "select * from v_student where student_id=?";
		return jdbcTemplate.queryForMap(sql, id);
	}

	public List<Map<String, Object>> getForPicker() throws Exception {
		String sql = "select u.*, a.auth_type, s.* from v_user u left join v_student s on u.user_id=s.student_id,"
				+ " v_user_auth a where u.user_id = a.user_id and u.status = 1 and a.auth_type = 1 order by u.username asc";
		return jdbcTemplate.queryForList(sql);
	}
}
