package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerAction extends HttpServlet {
	private Map commandMap = new HashMap(); // ��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����

	public void init(ServletConfig config) throws ServletException {
		// Common properties
		loadProperties("com/app/properties/Command");
	}

	// properties ����
	private void loadProperties(String path) {
		ResourceBundle rbHome = ResourceBundle.getBundle(path);// ������ ���������� rb�� ����.
		Enumeration<String> actionEnumHome = rbHome.getKeys();
		while (actionEnumHome.hasMoreElements()) {
			String command = actionEnumHome.nextElement();
			String className = rbHome.getString(command);
			try {
				Class commandClass = Class.forName(className); // �ش� ���ڿ��� Ŭ������ �����
				Object commandInstance = commandClass.newInstance(); // �ش� Ŭ������ ��ü�� ����
				commandMap.put(command, commandInstance); // Map ��ü�� commandMap�� ��ü ����
			} catch (ClassNotFoundException e) {
				continue; // error
				// throw new ServletException(e);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		requestPro(request, response);
	}

	// ������� ��û�� �м��ؼ� �ش� �۾��� ó��
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if (command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction) commandMap.get(command);
			if (com == null) {
				System.out.println("not found : " + command);
				return;
			}
			view = com.requestPro(request, response);
			if (view == null) {
				return;
			}
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		if (view == null)
			return;
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}