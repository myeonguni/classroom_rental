package com.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.beans.user;

public class userDao extends CommonDao {
	public static userDao getInstance() {
		userDao _instance = new userDao();
		return _instance;
	}
	
	/* ȸ������ */
	public boolean getUserJoin(user User) {
		ResultSet rs = null;
	    boolean result = false;
		String sql = "INSERT INTO STUDENT VALUES('"+User.getId()+"','"+User.getPassword()+"','"+User.getName()+"','"+User.getDepartment()+"','"+User.getTel()+"')";
	    try {
			rs = openConnection().executeQuery(sql);
			result = rs.next(); // ȸ�� �߰��� ture
		} catch (SQLException e) {
			result = false; // ȸ�� �߰� ���н� false
		} finally {
		    closeConnection();
		}
	    return result;
	}

	/* �α��� */
	public String getUserLogin(String id, String password) throws SQLException {
		ResultSet rs = null;
	    String result = null;
	    String sql = "SELECT * FROM STUDENT WHERE STD_ID = '"+id+"'";
        rs = openConnection().executeQuery(sql); // sql�� �����ϱ����� ������ ���� ������ �����ϰ� rs�� ��ȯ
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
