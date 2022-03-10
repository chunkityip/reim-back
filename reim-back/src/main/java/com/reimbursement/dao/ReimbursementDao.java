package com.reimbursement.dao;

import java.sql.SQLException;
import java.util.List;

import com.reimbursement.models.Reimbursement;

public interface ReimbursementDao {

	int addReimbursement(Reimbursement reimbursement);

	List<Reimbursement> searchReimbursements(int userid) throws SQLException;
	
	List<Reimbursement> getAllReimbursements() throws SQLException;

	int updateReimbursement(Reimbursement reimbursement);

}
