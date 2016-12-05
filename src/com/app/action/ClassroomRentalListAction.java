package com.app.action;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.classroom;
import com.app.beans.reservation;
import com.app.beans.timetable;
import com.app.controller.CommandAction;
import com.app.dao.classroomDao;

public class ClassroomRentalListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		// category1:��¥
		Calendar toWeek = Calendar.getInstance();
		String tmp_toWeek = toWeek.get(Calendar.YEAR) + "-W" + toWeek.get(Calendar.WEEK_OF_YEAR);
		
		// category2:�ǹ�
		ArrayList<String> buildingList = new ArrayList<String>();
		buildingList = classroomDao.getInstance().getBuildingList();

		// category3:���ǽ�
		ArrayList<classroom> classRoomList = new ArrayList<classroom>();
		classRoomList = classroomDao.getInstance().getClassRoomList(buildingList.get(0));

		// ���� ���� �ð�ǥ
		ArrayList<timetable> timeTableList = new ArrayList<timetable>();
		timeTableList = classroomDao.getInstance().getTimeTableList(classRoomList.get(0).getId());

		// ���ǽ� ���� ��Ȳ
		ArrayList<reservation> reservationTableList = new ArrayList<reservation>();
		reservationTableList = classroomDao.getInstance().getReservationTableList(tmp_toWeek, classRoomList.get(0).getId());

		
		/** 
		 * ���� ���� �ð�ǥ �� ���ǽ� ���� ��Ȳ�� ���� 2���� �迭 : ����/�ð�
		 * [1]�������ǽð�ǥ, [2]�������, [3]������, [4]����ݷ�
		 **/
		String timeTables[][] = new String[7][48];
		/* ���� ���� �ð�ǥ ��� */
		for (int i = 0; i < timeTableList.size(); i++) {
			for (int j = 0; j < timeTableList.get(i).getTime().length; j++) {
				timeTables[(Integer.parseInt(timeTableList.get(i).getDate()) - 1)][(Integer
						.parseInt(timeTableList.get(i).getTime()[j]) - 1)] = "[1]"+timeTableList.get(i).getName();
			}
		}
		/* ���ǽ� ���� ��Ȳ ��� */
		for (int i = 0; i < reservationTableList.size(); i++) {
			String[] tmp_time = reservationTableList.get(i).getRental_chk_time().split(",");
			String tmp_state = "";
			if (reservationTableList.get(i).getRental_state().equals("true")) {
				tmp_state = "[2]";
			} else if (reservationTableList.get(i).getRental_state().equals("false")) {
				tmp_state = "[3]";
			} else if (reservationTableList.get(i).getRental_state().equals("reject")) {
				tmp_state = "[4]";
			} else { }
			
			for (int j = 0; j < tmp_time.length; j++) {
				timeTables[(Integer.parseInt(reservationTableList.get(i).getRental_date()) - 1)][(Integer
						.parseInt(tmp_time[j]) - 1)] = tmp_state + reservationTableList.get(i).getUser_name();
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
					// ���� �ð�ǥ �� ���� ��Ȳ�� ������ ���
					String timeTablesTemp = timeTables[j][i];
					// ���� : [1]�������ǽð�ǥ=���, [2]�������=�Ķ�, [3]������=����
					if (timeTablesTemp.substring(0, 3).equals("[1]")) {
						timeTablesTemp = (timeTablesTemp.length() > 8) ? timeTablesTemp.substring(3, 8) + ".." : timeTablesTemp.substring(3, timeTablesTemp.length());
						timeTablesResult += "<td class='text-center' nowrap style='height:34px; overflow: hidden; font-size:12px; background-color:#fcf8e3;'>" + timeTablesTemp + "</td>";
					} else if (timeTablesTemp.substring(0, 3).equals("[2]")) {
						timeTablesTemp = (timeTablesTemp.length() > 8) ? "[����]" + timeTablesTemp.substring(3, 8) + ".." : "[����]" + timeTablesTemp.substring(3, timeTablesTemp.length());
						timeTablesResult += "<td class='text-center' nowrap style='height:34px; overflow: hidden; font-size:12px; background-color:#d9edf7;'>" + timeTablesTemp + "</td>";
					} else if (timeTablesTemp.substring(0, 3).equals("[3]")) {
						timeTablesTemp = (timeTablesTemp.length() > 8) ? "[���]" + timeTablesTemp.substring(3, 8) + ".." : "[���]" + timeTablesTemp.substring(3, timeTablesTemp.length());
						timeTablesResult += "<td class='text-center' nowrap style='height:34px; overflow: hidden; font-size:12px; background-color:#f2dede;'>" + timeTablesTemp + "</td>";
					} else if (timeTablesTemp.substring(0, 3).equals("[4]")) {
						timeTablesResult += "<td style='height:34px;'></td>";
					} else {
						
					}
					
				}
			}
			timeTablesResult += "</tr>";
		}

		request.setAttribute("category1", tmp_toWeek); // category1 ������� �信 ������
		request.setAttribute("category2", buildingList); // category2 ����� �信 ������
		request.setAttribute("category3", classRoomList); // category3 ������� �信 ������
		request.setAttribute("timetable", timeTablesResult);
		return "/views/rental/classroom_rental_list.jsp";
	}

}