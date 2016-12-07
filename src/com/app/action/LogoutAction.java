package com.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.controller.CommandAction;

public class LogoutAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// ���� ����
		request.getSession().invalidate();
		return "/";
	}

}