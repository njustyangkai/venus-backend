package com.venus.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.venus.mapper.UserRowMapper;
import com.venus.pojo.User;

@Repository
public class UserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public boolean modifyPwd(String userId, JSONObject newPwd) throws Exception {
		String sql = "update v_user set password=? where id=?";
		return jdbcTemplate.update(sql, newPwd.getString("newPassword"), userId) > 0;
	}

	public boolean isPwdRight(JSONObject pwd) throws Exception {
		String sql = "select * from v_user where id=? and password=?";
		List<User> list = jdbcTemplate.query(sql, new UserRowMapper(), pwd.getString("userId"),
				pwd.getString("password"));
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

}
