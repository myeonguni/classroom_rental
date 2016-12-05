package com.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.user;
import com.app.controller.CommandAction;
import com.app.dao.userDao;

public class JoinAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		boolean result;

		request.setCharacterEncoding("utf-8");

		user User = new user();
		User.setId(request.getParameter("id"));
		User.setPassword(request.getParameter("password"));
		User.setName(request.getParameter("name"));
		User.setDepartment(request.getParameter("department"));
		User.setTel(request.getParameter("tel"));
		result = userDao.getInstance().getUserJoin(User);

		if(result){
			request.setAttribute("success", "ȸ�����Կ� �����ϼ̽��ϴ�. �α��� ���ּ���.");
			return "/views/main/login.jsp";
		}else{
			request.setAttribute("error", "�̹� �����ϴ� �й��Դϴ�. Ȯ�� �� �ٽ� �õ����ּ���.");
			return "/views/main/join.jsp";
		}
	}
}
