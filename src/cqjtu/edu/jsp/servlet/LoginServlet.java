package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cqjtu.edu.studentadmin.service.LoginService;



public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
    LoginService loginService=new LoginService();


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("welcome");
		String name=request.getParameter("userName");
		String password=request.getParameter("password");
		System.out.println("用户名"+name);
		System.out.println("密码"+password);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String name=request.getParameter("userName");
		String password=request.getParameter("password");
		JSONObject json = new JSONObject();
		try {
			if(loginService.loginCheck(name, password)) {
				json.put("code", 1);
			}else {
				json.put("code", 0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		pw.close();
	}

}
