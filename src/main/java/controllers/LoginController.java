package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.logindao;
import models.User;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletContext ctx;
	RequestDispatcher rd;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ctx = getServletContext();
		rd = ctx.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User obj = new User();
		String usr = request.getParameter("username");
		String pwd = request.getParameter("password");

		if (request.getParameter("req_type") != null) {
			obj.setUsername(usr);
			obj.setPassword(pwd);
			logindao x = new logindao();
			int resp = x.login(obj);
			if (resp == 1) {
				request.setAttribute("usr", usr);
				System.out.println("success");
				ctx = getServletContext();
				rd = ctx.getRequestDispatcher("/Dashboard.jsp");
				rd.forward(request, response);
			} else {
				if (request.getParameter("req_type").equals("delete")) {
					obj.setUsername(usr);
					logindao x1 = new logindao();
					System.out.println("deleting");
					x1.Delete(obj);
//					if (resp1 == 1) {
//						
//						request.setAttribute("usr", usr);
//						System.out.println("success");
//						ctx = getServletContext();
//						rd = ctx.getRequestDispatcher("/Dashboard.jsp");
//						rd.forward(request, response);
//					}
//				} else {
//					System.out.println("failure");
					ctx = getServletContext();
					rd = ctx.getRequestDispatcher("/Dashboard.jsp");
					rd.forward(request, response);
				}
			}
		} else {
			obj.setUsername(usr);
			obj.setPassword(pwd);
			logindao x = new logindao();
			x.register(obj);
			x.View();
			ctx = getServletContext();
			rd = ctx.getRequestDispatcher("/Dashboard.jsp");
			rd.forward(request, response);
		}
	}

}
