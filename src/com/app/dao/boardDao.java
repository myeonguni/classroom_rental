package com.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.beans.board;
import com.app.beans.timetable;
import com.app.beans.user;

public class boardDao extends CommonDao {

	public static boardDao getInstance() {
		boardDao _instance = new boardDao();
		return _instance;
	}

	/* 게시글 리스트 조회 */
	public ArrayList<board> getBoardList() throws SQLException {
		ResultSet rs = null;
		ArrayList<board> result = new ArrayList<board>();
		String sql = "SELECT * FROM BOARD";
		rs = openConnection().executeQuery(sql);
		while (rs.next()) {
			board bb = new board();
			bb.setBoard_index(rs.getString("BOARD_ID"));
			bb.setUser_id(rs.getString("USER_ID"));
			bb.setUser_name(rs.getString("USER_NAME"));
			bb.setBoard_title(rs.getString("BOARD_TITLE"));
			bb.setBoard_body(rs.getString("BOARD_BODY"));
			bb.setBoard_bodyType(rs.getString("BOARD_BODYTYPE"));
			bb.setBoard_file(rs.getString("BOARD_FILE"));
			bb.setDate(rs.getString("BOARD_DATE"));
			result.add(bb);
		}
		closeConnection();
		return result;
	}

	/* 게시글 작성 */
	public boolean getBoardPost(board Board) {
		ResultSet rs = null;
		boolean result = false;
		String sql = "INSERT INTO BOARD VALUES(RES_ID_SEQ.NEXTVAL,'" + Board.getUser_id() + "','" + Board.getUser_name()
				+ "','" + Board.getBoard_title() + "','" + Board.getBoard_body() + "','" + Board.getBoard_bodyType()
				+ "','" + Board.getBoard_file() + "','" + Board.getDate() + "')";

		try {
			rs = openConnection().executeQuery(sql);
			result = rs.next(); // 게시글 추가시 ture
		} catch (SQLException e) {
			System.err.println(e);
			result = false; // 게시글 추가 실패시 false
		} finally {
			closeConnection();
		}
		return result;
	}

	/* 게시글 읽기 */
	public board getBoardPostRead(String postIndex) throws SQLException {
		ResultSet rs = null;
		board result = new board();
		/* 게시글 내용 읽어오기 */
		String sql = "SELECT * FROM BOARD WHERE BOARD_ID ='" + postIndex + "'";
		rs = openConnection().executeQuery(sql);
		while (rs.next()) {
			result.setBoard_index(rs.getString("BOARD_ID"));
			result.setUser_id(rs.getString("USER_ID"));
			result.setUser_name(rs.getString("USER_NAME"));
			result.setBoard_title(rs.getString("BOARD_TITLE"));
			result.setBoard_body(rs.getString("BOARD_BODY"));
			result.setBoard_bodyType(rs.getString("BOARD_BODYTYPE"));
			result.setBoard_file(rs.getString("BOARD_FILE"));
			result.setDate(rs.getString("BOARD_DATE"));
		}
		/* 게시글 댓글 읽어오기 INNER JOIN 사용 */
		//sql = "SELECT RECOMMENT.CM_ID, RECOMMENT.CM_NAME, RECOMMENT.CM_TEXT FROM BOARD INNER JOIN RECOMMENT ON BOARD.BOARD_ID = RECOMMENT.BOARD_ID WHERE BOARD.BOARD_ID = '" + postIndex + "'";
		
		closeConnection();
		return result;
	}

	
	
	/* 게시글 삭제 */
	public boolean getDeletePost(String postIndex) throws SQLException {
		ResultSet rs = null;
		boolean result = false;
		String sql = "DELETE FROM BOARD WHERE BOARD_ID ='" + postIndex + "'";

		try {
			rs = openConnection().executeQuery(sql);
			result = rs.next(); // 게시글 추가시 ture
		} catch (SQLException e) {
			System.err.println(e);
			result = false; // 게시글 추가 실패시 false
		} finally {
			closeConnection();
		}
		return result;
	}

}