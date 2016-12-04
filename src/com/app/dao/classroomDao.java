package com.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.beans.classroom;
import com.app.beans.timetable;

public class classroomDao extends CommonDao {
	public static classroomDao getInstance() {
		classroomDao _instance = new classroomDao();
		return _instance;
	}

	/* 건물조회 */
	public ArrayList<String> getBuildingList() throws SQLException {
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();
	    String sql = "SELECT * FROM BUILDING";
        rs = openConnection().executeQuery(sql);
        while(rs.next()){
        	result.add(rs.getString("BD_ID"));
        }
	    closeConnection();
	    return result;
	}

	/* 강의실조회 */
	public ArrayList<classroom> getClassRoomList(String building) throws SQLException {
		ResultSet rs = null;
		ArrayList<classroom> result = new ArrayList<classroom>();
	    String sql = "SELECT * FROM CLASSROOM WHERE CR_BUILDING = '"+building+"'";
        rs = openConnection().executeQuery(sql);
        while(rs.next()){
        	classroom cr = new classroom();
        	cr.setId(rs.getString("CR_ID"));
        	cr.setName(rs.getString("CR_NAME"));
        	result.add(cr);
        }
	    closeConnection();
	    return result;
	}

	/* 시간표조회 */
	public ArrayList<timetable> getTimeTableList(String classroom) throws SQLException {
		ResultSet rs = null;
		ArrayList<timetable> result = new ArrayList<timetable>();
	    String sql = "SELECT * FROM TIMETABLE WHERE TT_CR = '"+classroom+"'";
        rs = openConnection().executeQuery(sql);
        while(rs.next()){
        	timetable tt = new timetable();
        	tt.setDate(rs.getString("TT_DATE"));
        	String temp[] = rs.getString("TT_TIME").split(",");
        	tt.setTime(temp);
        	tt.setName(rs.getString("TT_NAME"));
        	result.add(tt);
        }
	    closeConnection();
	    return result;
	}
	
}
