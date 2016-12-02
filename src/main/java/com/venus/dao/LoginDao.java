package com.venus.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;

@Repository
public class LoginDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> login(JSONObject jsonObject) throws Exception {
		String sql = "select * from v_user u where u.username=? and u.password=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, jsonObject.getString("username"),
				jsonObject.getString("password"));
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	public String getRole(String userId) throws Exception {
		String sql = "select a.auth_type from v_user_auth a where a.user_id=?";
		return String.valueOf(jdbcTemplate.queryForObject(sql, Integer.class, userId));
	}

	public void changeLastLogTime(String userId) throws Exception {
		String sql = "update v_user set last_log_time=now() where user_id=?";
		jdbcTemplate.update(sql, userId);
	}
}
