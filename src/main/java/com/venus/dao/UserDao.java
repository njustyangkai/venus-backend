package com.venus.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.venus.frame.constants.Constants;

@Repository
public class UserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public boolean modifyPwd(String userId, JSONObject newPwd) throws Exception {
		String sql = "update v_user set password=? where user_id=?";
		return jdbcTemplate.update(sql, newPwd.getString("newPassword"), userId) > 0;
	}

	public boolean isPwdRight(JSONObject pwd) throws Exception {
		String sql = "select * from v_user where user_id=? and password=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, pwd.getString("userId"),
				pwd.getString("password"));
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean isUsernameUsed(JSONObject username) throws Exception {
		String sql = "select * from v_user where username=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, username.getString("username"));
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean addUser(String id, JSONObject user) throws Exception {
		String sql = "insert into v_user(user_id,username,password,create_time,last_log_time,status) values(?,?,?,now(),now(),?)";
		return jdbcTemplate.update(sql, id, user.getString("username"), user.getString("password"),
				Constants.USER_LOCK) > 0;
	}

	public boolean addRole(String id, int role) throws Exception {
		String sql = "insert into v_user_auth(user_id, auth_type) values(?, ?)";
		return this.jdbcTemplate.update(sql, id, role) > 0;
	}

	public void delUser(String id) throws Exception {
		String sql = "delete from v_user where user_id=?";
		jdbcTemplate.update(sql, id);
	}

	public void delRole(String id) throws Exception {
		String sql = "delete from v_user_auth where user_id=?";
		jdbcTemplate.update(sql, id);
	}

	public void changeStatus(String id, int status) throws Exception {
		String sql = "update v_user set status=? where user_id =?";
		jdbcTemplate.update(sql, status, id);
	}

}
