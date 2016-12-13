package com.app.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.reservation;
import com.app.controller.CommandAction;
import com.app.dao.adminDao;

public class AdminRentalMngAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		ArrayList<reservation> rentalReportList = adminDao.getInstance().getRentalReportList();
		request.setAttribute("rentalReportList", rentalReportList); // ���õ� ���ǽ� �뿩 ��û�� ����Ʈ�� �信 ������
		
		return "/views/admin/rental_mng.jsp";
	}
}
