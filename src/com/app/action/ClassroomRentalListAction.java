package com.app.action;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.classroom;
import com.app.beans.timetable;
import com.app.controller.CommandAction;
import com.app.dao.classroomDao;

public class ClassroomRentalListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		// category1:��¥
		Calendar toWeek = Calendar.getInstance();
		
		// category2:�ǹ�
		ArrayList<String> buildingList = new ArrayList<String>();
		buildingList = classroomDao.getInstance().getBuildingList();

		// category3:���ǽ�
		ArrayList<classroom> classRoomList = new ArrayList<classroom>();
		classRoomList = classroomDao.getInstance().getClassRoomList(buildingList.get(0));

		// ���� �ð�ǥ
		ArrayList<timetable> timeTableList = new ArrayList<timetable>();
		timeTableList = classroomDao.getInstance().getTimeTableList(classRoomList.get(0).getId());

		/** ���� �ð�ǥ�� ���� 2���� �迭 : ����/�ð� **/
		String timeTables[][] = new String[7][48];
		
		for (int i = 0; i < timeTableList.size(); i++) {
			for (int j = 0; j < timeTableList.get(i).getTime().length; j++) {
				timeTables[(Integer.parseInt(timeTableList.get(i).getDate()) - 1)][(Integer
						.parseInt(timeTableList.get(i).getTime()[j]) - 1)] = timeTableList.get(i).getName();
			}
		}

		/** View�� ��ȯ�� ���̺� �ۼ� **/
		String timeTablesResult = "";
		for (int i = 0; i < timeTables[0].length; i++) {
			if (i % 2 == 0) {
				timeTablesResult += "<tr><td rowspan='2' class='text-center' style='background-color: #f0ad4e; vertical-align:middle;'>"
						+ String.format("%02d", (i / 2)) + ":00</td>";
			} else {
				timeTablesResult += "<tr>";
			}
			for (int j = 0; j < timeTables.length; j++) {
				if (timeTables[j][i] == null) {
					timeTablesResult += "<td style='height:34px;'></td>";
				} else {
					String timeTablesTemp = (timeTables[j][i].length() > 5) ? timeTables[j][i].substring(0, 5) + ".."
							: timeTables[j][i];
					timeTablesResult += "<td class='text-center' nowrap style='height:34px; overflow: hidden; font-size:12px; background-color:#e6bc7f;'>"
							+ timeTablesTemp + "</td>";
				}
			}
			timeTablesResult += "</tr>";
		}

		request.setAttribute("category1", toWeek.get(Calendar.YEAR) + "-W" + toWeek.get(Calendar.WEEK_OF_YEAR)); // category1 ������� �信 ������
		request.setAttribute("category2", buildingList); // category2 ����� �信 ������
		request.setAttribute("category3", classRoomList); // category3 ������� �信 ������
		request.setAttribute("timetable", timeTablesResult);
		return "/views/rental/classroom_rental_list.jsp";
	}

}