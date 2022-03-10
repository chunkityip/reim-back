package com.reimbursement.controllers;

import com.reimbursement.models.GenericResponse;
import com.reimbursement.models.User;
import com.reimbursement.services.UserServices;
import com.reimbursement.services.UserServicesImpl;

import io.javalin.http.Handler;
import io.javalin.http.InternalServerErrorResponse;

public class UserController {

	UserServices usersServices = new UserServicesImpl();

	public Handler userLogon = ctx -> {
		User user = ctx.bodyAsClass(User.class);
		user = usersServices.userLogon(user);
		ctx.json(user);
	};

	public Handler searchUsers = ctx -> {
		Integer roleid = Integer.valueOf(ctx.queryParam("roleid"));
		ctx.json(usersServices.searchUsers(roleid));
	};

	public Handler updateUser = ctx -> {
		User user = ctx.bodyAsClass(User.class);

		int result = usersServices.updateUser(user);
		if (result > 0) {
			ctx.json(new GenericResponse("success"));
		} else {
			throw new InternalServerErrorResponse();
		}
	};
}
