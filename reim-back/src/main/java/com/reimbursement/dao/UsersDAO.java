package com.reimbursement.dao;

import java.sql.SQLException;
import java.util.List;

import com.reimbursement.models.User;

public interface UsersDAO {

	User checkLogin(String uname, String upass) throws SQLException;

	List<User> searchUsers(int roleid) throws SQLException;
	
	User getUser(int userid) throws SQLException;
	
	int updateUser(User user);
}
