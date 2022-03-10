package com.reimbursement.services;

import java.sql.SQLException;
import java.util.List;

import com.reimbursement.models.Reimbursement;

public interface ReimbursementServices {
	public int addReimbursement(Reimbursement reimbursement) throws SQLException;

	public List<Reimbursement> searchReimbursements(int empId) throws SQLException;

	public int updateReimbursement(Reimbursement reimbursement) throws SQLException;
	
	public List<Reimbursement> getAllReimbursements() throws SQLException;

}
