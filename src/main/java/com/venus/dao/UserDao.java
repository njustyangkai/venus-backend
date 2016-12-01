package com.venus.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.venus.frame.constants.Constants;
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

	public boolean isUsernameUsed(JSONObject username) throws Exception {
		String sql = "select * from v_user where username=?";
		List<User> list = jdbcTemplate.query(sql, new UserRowMapper(), username.getString("username"));
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean addUser(String id, JSONObject user) throws Exception {
		String sql = "insert into v_user(id,username,password,createtime,lastlogtime,state) values(?,?,?,now(),now(),?)";
		return jdbcTemplate.update(sql, id, user.getString("username"), user.getString("password"),
				Constants.USER_LOCK) > 0;
	}

	public boolean addRole(String id) throws Exception {
		String sql = "insert into v_user_auth(user_id, auth_type) values(?, ?)";
		return this.jdbcTemplate.update(sql, id, Constants.AUTH_TYPE_STUDENT) > 0;
	}

	public void delUser(String id) throws Exception {
		String sql = "delete from v_user where id=?";
		jdbcTemplate.update(sql, id);
	}

	public void delRole(String id) throws Exception {
		String sql = "delete from v_user_auth where user_id=?";
		jdbcTemplate.update(sql, id);
	}

}
