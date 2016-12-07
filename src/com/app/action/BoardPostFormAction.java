package com.app.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.board;
import com.app.beans.user;
import com.app.controller.CommandAction;
import com.app.dao.boardDao;
import com.app.dao.userDao;

public class BoardPostFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		boolean result;

		request.setCharacterEncoding("utf-8");

		// 글작성 날짜 및 시간을 위한  date 객체
		Date toDate = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Board 객체 생성
		board Board = new board();
		Board.setUser_id(request.getParameter("user_id"));
		Board.setUser_name(request.getParameter("user_name"));
		Board.setBoard_title(request.getParameter("board_title"));
		Board.setBoard_body(request.getParameter("board_body"));
		Board.setBoard_bodyType(request.getParameter("board_bodyType"));
		Board.setBoard_file(request.getParameter("board_file"));
		Board.setDate(sd.format(toDate));
		
		result = boardDao.getInstance().getBoardPost(Board);
		request.setAttribute("result", result);
		System.out.println(result);
		
		return "/views/board/board_post.jsp";
	}
}
