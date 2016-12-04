package com.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.beans.user;

public class userDao extends CommonDao {
	public static userDao getInstance() {
		userDao _instance = new userDao();
		return _instance;
	}
	
	/* 회원가입 */
	public boolean getUserJoin(user User) {
		ResultSet rs = null;
	    boolean result = false;
		String sql = "INSERT INTO STUDENT VALUES('"+User.getId()+"','"+User.getPassword()+"','"+User.getName()+"','"+User.getDepartment()+"','"+User.getTel()+"')";
	    try {
			rs = openConnection().executeQuery(sql);
			result = rs.next(); // 회원 추가시 ture
		} catch (SQLException e) {
			result = false; // 회원 추가 실패시 false
		} finally {
		    closeConnection();
		}
	    return result;
	}

	/* 로그인 */
	public String getUserLogin(String id, String password) throws SQLException {
		ResultSet rs = null;
	    String result = null;
	    String sql = "SELECT * FROM STUDENT WHERE STD_ID = '"+id+"'";
        rs = openConnection().executeQuery(sql); // sql을 실행하기위해 연결을 열어 쿼리를 실행하고 rs에 반환
        if(rs.next()){
        	if(rs.getString("STD_PWD").equals(password)){
        		result = rs.getString("STD_ID");
        		result += "/"+rs.getString("STD_NAME");
        		result += "/"+rs.getString("STD_DEPARTMENT");
        		result += "/"+rs.getString("STD_TEL");
        	}else{
        		result = "2";
        	}
        }else{
        	result = "1";
        }

	    closeConnection();
	    return result;
	}
}
