package com.app.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.board;
import com.app.beans.boardComment;
import com.app.controller.CommandAction;
import com.app.dao.boardDao;

public class BoardPostReadAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		// �Խñ� ���� ��������
		board boardRead = boardDao.getInstance().getBoardPostRead(request.getParameter("board_index"));
		
		// �ش� �Խñ� ��� ����Ʈ ��������
		ArrayList<boardComment> boardCommentList = boardDao.getInstance().getBoardCommentList(request.getParameter("board_index"));
		
		
		// �Խñ� ���� ���� ���� �信 �����带 ���� ���ǽ�
		// ȸ���̸�
		if (request.getSession().getAttribute("id") != null)
		{
			// �� �ۼ��� Ȥ�� �����ڰ� ��û���� ���
			if (boardRead.getUser_id().equals(request.getSession().getAttribute("id")) || request.getSession().getAttribute("isAdmin").equals("1"))
			{
				request.setAttribute("mng", "1"); // �� �ۼ�����
			}
			// �ƴҰ��
			else
			{
				request.setAttribute("mng", "2"); // �� �ۼ��ڰ� �ƴ�
			}
		}
		// ��ȸ���̸�
		else
		{
			request.setAttribute("mng", "3"); // ��ȸ����
		}
		
		// �Խñ� ���� �信 ������
		request.setAttribute("boardRead", boardRead);
		
		// �ش� �Խñ� ��� ����Ʈ �信 ������
		request.setAttribute("boardCommentList", boardCommentList);
		
		
		return "/views/board/board_read.jsp";
	}
}
