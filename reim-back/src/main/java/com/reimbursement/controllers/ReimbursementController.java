package com.reimbursement.controllers;

import java.util.List;

import com.reimbursement.models.GenericResponse;
import com.reimbursement.models.Reimbursement;
import com.reimbursement.services.ReimbursementServices;
import com.reimbursement.services.ReimbursementServicesImpl;

import io.javalin.http.Handler;
import io.javalin.http.InternalServerErrorResponse;

public class ReimbursementController {

	ReimbursementServices services = new ReimbursementServicesImpl();

	public Handler addReimbursement = ctx -> {
		Reimbursement reimb = ctx.bodyAsClass(Reimbursement.class);

		int result = services.addReimbursement(reimb);
		if (result > 0) {
			ctx.json(new GenericResponse("success"));
		} else {
			throw new InternalServerErrorResponse();
		}
	};

	public Handler searchReimbursements = ctx -> {
		Integer empid = Integer.valueOf(ctx.queryParam("empid"));
		List<Reimbursement> remList = services.searchReimbursements(empid);
		ctx.json(remList);
	};
	
	public Handler getAllReimbursements = ctx -> {
		List<Reimbursement> remList = services.getAllReimbursements();
		ctx.json(remList);
	};
	
	public Handler updateReimbursement = ctx -> {
		Reimbursement reimb = ctx.bodyAsClass(Reimbursement.class);

		int result = services.updateReimbursement(reimb);
		if (result > 0) {
			ctx.json(new GenericResponse("success"));
		} else {
			throw new InternalServerErrorResponse();
		}
	};

}
