package com.app.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.classroom;
import com.app.controller.CommandAction;
import com.app.dao.classroomDao;

public class ClassroomRentalListCategoryAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		// category3:���ǽ�
		ArrayList<classroom> classRoomList = new ArrayList<classroom>();
		classRoomList = classroomDao.getInstance().getClassRoomList(request.getParameter("category2"));
		request.setAttribute("category3", classRoomList); // category3 ������� �信 ������
		return "/views/rental/rental_event/tmp_category_list.jsp";
	}
}
