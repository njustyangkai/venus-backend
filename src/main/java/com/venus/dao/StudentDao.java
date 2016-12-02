package com.venus.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> getStudents() throws Exception {
		String sql = "select * from v_user u left join v_student s on u.user_id=s.student_id order by u.username asc";
		return jdbcTemplate.queryForList(sql);
	}
}
