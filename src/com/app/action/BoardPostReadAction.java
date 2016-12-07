package com.app.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.board;
import com.app.controller.CommandAction;
import com.app.dao.boardDao;

public class BoardPostReadAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		board boardRead = boardDao.getInstance().getBoardPostRead(request.getParameter("postIndex"));
		request.setAttribute("boardRead", boardRead); // ���õ� �Խñ��� �信 ������
		
		return "/views/board/board_read.jsp";
	}
}
