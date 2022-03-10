package com.reimbursement.services;

import java.sql.SQLException;
import java.util.List;

import com.reimbursement.dao.UsersDAO;
import com.reimbursement.dao.UsersDAOImpl;
import com.reimbursement.models.User;

public class UserServicesImpl implements UserServices {
	UsersDAO userDao = new UsersDAOImpl();

	@Override
	public User userLogon(User user) throws SQLException {
		return userDao.checkLogin(user.getUserName(), user.getUserPassword());
	}

	@Override
	public List<User> searchUsers(int roleid) throws SQLException {
		return userDao.searchUsers(roleid);
	}

	@Override
	public User getUser(int userid) throws SQLException {
		return userDao.getUser(userid);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		User existingUser = userDao.getUser(user.getUserID());
		if (user.getEmail() != null) {
			existingUser.setEmail(user.getEmail());
		}

		if (user.getFirstName() != null) {
			existingUser.setFirstName(user.getFirstName());
		}

		if (user.getLastName() != null) {
			existingUser.setLastName(user.getLastName());
		}
		return userDao.updateUser(existingUser);
	}

}
