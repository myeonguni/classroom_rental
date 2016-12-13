package com.app.dao;

import java.sql.*;

public class CommonDao {

	// ���������� ���� ���ӿ� ���õ� ���� �������� ����� ����
	private final String driverName = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracle:thin:@finalwebproject.c82x1jiwzyrm.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	private final String db_id = "root";
	private final String db_pw = "12345678";
	// ���ӿ� �ʿ��� ������ ����
	private Connection con = null;
	private Statement stmt = null;
	// db ���������� ������, �����Ŀ� SQL���� ����ϱ����� �ʿ��� statement��ü�� ��ȯ�ϴ� openConnection �޼ҵ带 ����
	public Statement openConnection() {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, db_id, db_pw);
			stmt = con.createStatement();
		} catch (Exception e) {
			System.err.println("Oracle DB ���� ���� �߻�!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return stmt;
	}

	// ������ �����ϱ����� closeConnection �޼ҵ带 ����
	public void closeConnection() {
		try {
			if (!con.isClosed()) // ������ �ʾ�����
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}