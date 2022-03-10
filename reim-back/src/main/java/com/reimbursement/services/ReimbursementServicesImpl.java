package com.reimbursement.services;

import java.sql.SQLException;
import java.util.List;

import com.reimbursement.dao.ReimbursementDAOlmpl;
import com.reimbursement.dao.ReimbursementDao;
import com.reimbursement.models.Reimbursement;

public class ReimbursementServicesImpl implements ReimbursementServices {
	ReimbursementDao dao = new ReimbursementDAOlmpl();

	@Override
	public int addReimbursement(Reimbursement reimbursement) throws SQLException {
		return dao.addReimbursement(reimbursement);
	}

	@Override
	public List<Reimbursement> searchReimbursements(int empId) throws SQLException {
		return dao.searchReimbursements(empId);
	}

	@Override
	public int updateReimbursement(Reimbursement reimbursement) throws SQLException {
		return dao.updateReimbursement(reimbursement);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		return dao.getAllReimbursements();
	}

}
