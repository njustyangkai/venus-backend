package com.venus.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.venus.mapper.StudentListRowMapper;
import com.venus.pojo.StudentInfo;

@Repository
public class StudentDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<StudentInfo> getStudents() throws Exception {
		String sql = "select * from v_user user,v_student student where user.id=student.id order by user.lastlogtime desc";
		return this.jdbcTemplate.query(sql, new StudentListRowMapper());
	}
}
