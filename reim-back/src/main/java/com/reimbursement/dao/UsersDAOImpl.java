package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reimbursement.models.User;

public class UsersDAOImpl implements UsersDAO {
	public static final Logger LOG = LogManager.getLogger(UsersDAOImpl.class);
	private PreparedStatement ps;

	private DBUtil dbutil = DBUtil.getConnectionFactory();
	Connection connection = dbutil.getConnection();

	/*
	 * checkLogin() will check if: return true if username and password are valid
	 * return false if not valid
	 */
	public User checkLogin(String uname, String upass) throws SQLException {
		User user = new User();

		String sql = "SELECT * FROM users WHERE users_name = ? AND password = ?";

		ps = connection.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, upass);

		ResultSet resultSet = ps.executeQuery();
		if (resultSet.next()) {
			user.setUserID(resultSet.getInt("users_id"));
			user.setUserName(resultSet.getString("users_name"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setEmail(resultSet.getString("email"));
			user.setRoleID(resultSet.getInt("users_role_id"));
		}

		return user;
	}

	@Override
	public List<User> searchUsers(int roleid) throws SQLException {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users WHERE users_role_id = ?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, roleid);
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setUserID(resultSet.getInt("users_id"));
			user.setUserName(resultSet.getString("users_name"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setEmail(resultSet.getString("email"));
			user.setRoleID(resultSet.getInt("users_role_id"));
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUser(int userid) throws SQLException {
		User user = new User();
		String sql = "SELECT * FROM users WHERE users_id = ?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, userid);
		ResultSet resultSet = ps.executeQuery();
		if (resultSet.next()) {
			user.setUserID(resultSet.getInt("users_id"));
			user.setUserName(resultSet.getString("users_name"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setEmail(resultSet.getString("email"));
			user.setRoleID(resultSet.getInt("users_role_id"));
		}
		return user;
	}

	@Override
	public int updateUser(User user) {
		int rowCount = 0;
		String sql = "UPDATE users set first_name = ?, last_name = ?, " + "email  = ? WHERE users_id  = ?";
		try {

			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getUserID());

			rowCount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}
