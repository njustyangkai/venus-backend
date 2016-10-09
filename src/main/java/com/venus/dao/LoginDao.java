package com.venus.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.venus.mapper.UserRowMapper;
import com.venus.pojo.User;

@Repository
public class LoginDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public User login(JSONObject jsonObject) throws Exception {
		String sql = "select * from v_user u where u.username=? and u.password=?";
		List<User> list = jdbcTemplate.query(sql, new UserRowMapper(), jsonObject.getString("name"), jsonObject.getString("password"));
		if (!CollectionUtils.isEmpty(list))
		{
			return list.get(0);
		}
		return null;
	}
	
	public String getRole(String userId) throws Exception {
		String sql = "select a.auth_type from v_user_auth a where a.user_id=?";
		return String.valueOf(jdbcTemplate.queryForObject(sql, Integer.class, userId));
	}
	
	public void changeLastLogTime(String userId) throws Exception {
		String sql = "update v_user set lastlogtime=now() where id=?";
		jdbcTemplate.update(sql, userId);
	}
}
