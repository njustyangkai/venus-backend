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
		String sql = "select * from v_user u left join v_student s on u.user_id=s.student_id order by u.username asc";
		return jdbcTemplate.queryForList(sql);
	}

	public boolean add(String id, JSONObject addInfo) throws Exception {
		String sql = "insert into v_student(student_id,name,email,grade,birthday,phone,parent_phone,sex) values(?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, id, addInfo.getString("name"), addInfo.getString("email"),
				addInfo.getString("grade"), addInfo.getDate("birthday"), addInfo.getString("phone"),
				addInfo.getString("parentPhone")) > 0;
	}

	public void del(String id) throws Exception {
		String sql = "delete from v_student where student_id=?";
		jdbcTemplate.update(sql, id);
	}
}
