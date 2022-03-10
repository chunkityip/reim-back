package com.reimbursement.services;

import java.sql.SQLException;
import java.util.List;

import com.reimbursement.models.User;

public interface UserServices {
	public User userLogon(User user) throws SQLException;

	public List<User> searchUsers(int userid) throws SQLException;

	public User getUser(int userid) throws SQLException;

	public int updateUser(User user) throws SQLException;
}
