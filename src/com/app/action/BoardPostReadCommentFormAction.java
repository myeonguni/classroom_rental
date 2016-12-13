package com.app.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.boardComment;
import com.app.controller.CommandAction;
import com.app.dao.boardDao;

public class BoardPostReadCommentFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String result = "";

		request.setCharacterEncoding("utf-8");

		// ��� �ۼ� ��¥ �� �ð��� ����  date ��ü
		Date toDate = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// boardComment ��ü ����
		boardComment Comment = new boardComment();
		Comment.setId(request.getParameter("commentId"));
		Comment.setName(request.getParameter("commentName"));
		Comment.setBody(request.getParameter("commentBody"));
		Comment.setDate(sd.format(toDate));
		Comment.setBoardid(request.getParameter("commentIndex"));
		
		// �ش� �Խù� �ε���
		String postIndex = request.getParameter("commentIndex");
		
		// ��� �߰�
		result = boardDao.getInstance().getBoardPostComment(Comment);
		
		request.setAttribute("result", result);
		
		return "/boardPostRead.do?board_index="+postIndex;
	}
}
