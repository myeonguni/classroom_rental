package com.app.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.timetable;
import com.app.controller.CommandAction;
import com.app.dao.classroomDao;

public class ClassroomRentalFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		System.out.println("d"+request.getParameter("category3"));
		ArrayList<timetable> timeTableList = new ArrayList<timetable>();
		System.out.println(request.getParameter("category3"));
		timeTableList = classroomDao.getInstance().getTimeTableList(request.getParameter("category3"));

		String timeTables[][] = new String[7][48];
		for (int i = 0; i < timeTableList.size(); i++) {
			for (int j = 0; j < timeTableList.get(i).getTime().length; j++) {
				timeTables[(Integer.parseInt(timeTableList.get(i).getDate()) - 1)][(Integer
						.parseInt(timeTableList.get(i).getTime()[j]) - 1)] = timeTableList.get(i).getName();
			}
		}

		String timeTablesResult = "";
		int count = 0;
		for (int i = 0; i < timeTables[0].length; i++) {
			if (i % 2 == 0) {
				timeTablesResult += "<tr style='background-color:#eee; font-size:13; font-family:Arial; padding:0px;'><td rowspan='2' class='text-center' style='color:black; background-color:#F0AD4E; vertical-align:middle; padding:0px;'>"
						+ String.format("%02d", (i / 2)) + ":00</td>";
			} else {
				timeTablesResult += "<tr>";
			}
			for (int j = 0; j < timeTables.length; j++) {
				if (timeTables[j][i] == null) {
					timeTablesResult += "<td class='text-center' style='padding:0px;'><input type='checkbox' name='chk_time' value='"
							+ (++count) + "'></td>";
				} else {
					++count;
					String timeTablesTemp = (timeTables[j][i].length() > 5) ? timeTables[j][i].substring(0, 5) + ".."
							: timeTables[j][i];
					timeTablesResult += "<td class='text-center' nowrap style='padding:0px; overflow: hidden; font-size:12px; background-color:#eee;'>"
							+ timeTablesTemp + "</td>";
				}
			}
			timeTablesResult += "</tr>";
		}

		request.setAttribute("timetable", timeTablesResult);
		return "/views/rental/rental_event/tmp_form_list.jsp";
	}
}
