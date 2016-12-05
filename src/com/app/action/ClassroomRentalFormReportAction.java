package com.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.reservation;
import com.app.controller.CommandAction;
import com.app.dao.classroomDao;

public class ClassroomRentalFormReportAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		boolean result = false;
		request.setCharacterEncoding("utf-8");
		
		String tmp_user_name = request.getParameter("user_name");
		String tmp_user_id = request.getParameter("user_id");
		String tmp_user_department = request.getParameter("user_department");
		String tmp_user_tel = request.getParameter("user_tel");
		String tmp_rental_name = request.getParameter("rental_name");
		String tmp_rental_alldate = request.getParameter("rental_alldate");
		String tmp_rental_reason = request.getParameter("rental_reason");
		// �ٹٲ� ġȯ
		tmp_rental_reason = tmp_rental_reason.replace("\r\n","<br>");
		String tmp_rental_agree = request.getParameter("rental_chk_agree");
		
		// ����ð��� 7�ַ� ������ ��� �������� �������� ������~�Ͽ��ϱ��� ����ð� �ֱ�(','�� ����)
		String[] tmp_rental_chk_time = request.getParameterValues("rental_chk_time");
		String[] tmp_rental_date = new String[7];
		// ""(����)���� �ʱ�ȭ
		for (int i=0; i<tmp_rental_date.length; i++) {
			tmp_rental_date[i] = "";
		}
		for (String temp : tmp_rental_chk_time) {
			if (Integer.parseInt(temp)%7 == 1) {			// ������ - �������� 1�� ���
				tmp_rental_date[0] += (Integer.parseInt(temp)/7)+1+",";
			} else if (Integer.parseInt(temp)%7 == 2) {	// ȭ���� - �������� 2�� ���
				tmp_rental_date[1] += (Integer.parseInt(temp)/7)+1+",";
			} else if (Integer.parseInt(temp)%7 == 3) {	// ������ - �������� 3�� ���
				tmp_rental_date[2] += (Integer.parseInt(temp)/7)+1+",";
			} else if (Integer.parseInt(temp)%7 == 4) {	// ����� - �������� 4�� ���
				tmp_rental_date[3] += (Integer.parseInt(temp)/7)+1+",";
			} else if (Integer.parseInt(temp)%7 == 5) {	// �ݿ��� - �������� 5�� ���
				tmp_rental_date[4] += (Integer.parseInt(temp)/7)+1+",";
			} else if (Integer.parseInt(temp)%7 == 6) {	// ����� - �������� 6�� ���
				tmp_rental_date[5] += (Integer.parseInt(temp)/7)+1+",";
			} else if (Integer.parseInt(temp)%7 == 0) {	// �Ͽ��� - �������� 0�� ���
				tmp_rental_date[6] += (Integer.parseInt(temp)/7)+",";
			} else {
				
			}
		}
		// ������ ��¥�� ������ ',' ����� beans ��ü�� ����� DB�� �߰��ϱ�
		for (int i=0; i<tmp_rental_date.length; i++) {
			// ����ð��� �����Ѵٸ� 
			if (!tmp_rental_date[i].equals("")) {
				// ������ ',' �����
				tmp_rental_date[i] = tmp_rental_date[i].substring(0, tmp_rental_date[i].length()-1);
				// reservation beans ��ü ���� �� �� ����
				reservation report = new reservation();
				report.setUser_name(tmp_user_name);
				report.setUser_id(tmp_user_id);
				report.setUser_department(tmp_user_department);
				report.setUser_tel(tmp_user_tel);
				report.setRental_name(tmp_rental_name);
				report.setRental_alldate(tmp_rental_alldate);
				report.setRental_date(String.valueOf(i+1));
				report.setRental_chk_time(tmp_rental_date[i]);
				report.setRental_reason(tmp_rental_reason);
				report.setRental_chk_agree("true");
				// DB�� ���ǽ� ��� ��û�� �߰�(RESERVATION TABLE)
				result = classroomDao.getInstance().getAddReservation(report);
			}
		}

		if(result){
			request.setAttribute("result", "1");
			return "/views/rental/classroom_rental_form.jsp";
		}else{
			request.setAttribute("result", "2");
			return "/views/rental/classroom_rental_form.jsp";
		}
	}
}
