package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reimbursement.models.Reimbursement;

public class ReimbursementDAOlmpl implements ReimbursementDao {
	public static final Logger LOG = LogManager.getLogger(ReimbursementDAOlmpl.class);
	private PreparedStatement ps;

	private DBUtil dbutil = DBUtil.getConnectionFactory();
	Connection connection = dbutil.getConnection();

	// ----------------------------------------------Employee----------------------------------------------
	/*
	 * addReimbursementsCase() will add data if return true if adding Reimbursements
	 * Case to database successfully return false if adding unsuccessful
	 */

	@Override
	public int addReimbursement(Reimbursement reimbursement) {

		int affectedRows = 0;

		String sql = "INSERT INTO reimbursement(reimbursement_amount, reimbursement_description, reimbursement_applicant_id, reimbursement_submit_time)"
				+ "values(? , ? , ? , CURRENT_TIMESTAMP)";

		try {
			ps = connection.prepareStatement(sql);
			ps.setDouble(1, reimbursement.getReimbursementsAmount());
			ps.setString(2, reimbursement.getReimbursementsDescription());
			ps.setInt(3, reimbursement.getReimbursementsApplicantID());

			affectedRows = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public List<Reimbursement> searchReimbursements(int userid) throws SQLException {
		List<Reimbursement> remList = new ArrayList<Reimbursement>();
		String sql = "SELECT * FROM reimbursement WHERE reimbursement_applicant_id = ?";

		ps = connection.prepareStatement(sql);
		ps.setInt(1, userid);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Reimbursement rem = new Reimbursement();
			rem.setReimbursementsID(rs.getInt("reimbursement_id"));
			rem.setReimbursementsAmount(rs.getInt("reimbursement_amount"));
			rem.setReimbursementsApplicantID(rs.getInt("reimbursement_applicant_id"));
			rem.setReimbursementsDescription(rs.getString("reimbursement_description"));
			rem.setReimbursementsResolvedTime(rs.getString("reimbursement_resolved_time"));
			rem.setReimbursementsSubmitTime(rs.getString("reimbursement_submit_time"));
			rem.setReimbursementsStatusID(rs.getInt("reimbursement_status_id"));
			remList.add(rem);
		}

		return remList;
	}

	@Override
	public int updateReimbursement(Reimbursement reimbursement) {
		int rowCount = 0;
		String sql = "UPDATE reimbursement set reimbursement_status_id = ? , "
				+ "reimbursement_resolved_time = CURRENT_TIMESTAMP WHERE reimbursement_id = ?";
		// This sql will update the info base on status id , time and reimbursement id
		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1, reimbursement.getReimbursementsStatusID());
			ps.setInt(2, reimbursement.getReimbursementsID());

			rowCount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		List<Reimbursement> remList = new ArrayList<Reimbursement>();
		String sql = "SELECT * FROM reimbursement";

		ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Reimbursement rem = new Reimbursement();
			rem.setReimbursementsID(rs.getInt("reimbursement_id"));
			rem.setReimbursementsAmount(rs.getInt("reimbursement_amount"));
			rem.setReimbursementsApplicantID(rs.getInt("reimbursement_applicant_id"));
			rem.setReimbursementsDescription(rs.getString("reimbursement_description"));
			rem.setReimbursementsResolvedTime(rs.getString("reimbursement_resolved_time"));
			rem.setReimbursementsSubmitTime(rs.getString("reimbursement_submit_time"));
			rem.setReimbursementsStatusID(rs.getInt("reimbursement_status_id"));
			remList.add(rem);
		}

		return remList;
	}
}