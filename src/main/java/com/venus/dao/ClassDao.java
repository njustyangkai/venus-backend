package com.venus.dao;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

@Repository
public class ClassDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public boolean add(String id, JSONObject addInfo) throws Exception {
		String sql = "insert into v_event(event_id,student_id,student_name,teacher_id,teacher_name,color,start_time,end_time) values(?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, id, addInfo.getString("studentId"), addInfo.getString("studentName"),
				addInfo.getString("teacherId"), addInfo.getString("teacherName"), addInfo.getString("color"),
				Timestamp.valueOf(addInfo.getString("startTime")), Timestamp.valueOf(addInfo.getString("endTime"))) > 0;
	}
}
