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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardPostFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		boolean result;

		request.setCharacterEncoding("utf-8");
		
		/*
		 * ���� ���ε� ����
		 * ���� ���� ������ ����, ���� ���� ���, ���� ���� ���ε�
		 */
		MultipartRequest multi = null;
		int sizeLimit = 100 * 1024 * 1024;
		String savePath = request.getRealPath("/uploads");
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// ���ۼ� ��¥ �� �ð��� ���� date ��ü
		Date toDate = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Board ��ü ����
		board Board = new board();
		Board.setUser_id(multi.getParameter("user_id"));
		Board.setUser_name(multi.getParameter("user_name"));
		Board.setBoard_title(multi.getParameter("board_title"));
		Board.setBoard_body(multi.getParameter("board_body"));
		Board.setBoard_bodyType(multi.getParameter("board_bodyType"));
		Board.setDate(sd.format(toDate));
		Board.setBoard_file(multi.getFilesystemName("board_file"));

		result = boardDao.getInstance().getBoardPost(Board);
		request.setAttribute("result", result);

		return "/views/board/board_post.jsp";
	}
}
