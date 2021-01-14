//可能有错误，第35行，StringUtils.isNullOrEmpty(no)
package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.*;

import cqjtu.edu.studentadmin.entity.Student;
import cqjtu.edu.studentadmin.service.StudentOperationImp;

/**
 * 瀛︾敓淇℃伅淇濆瓨
 */
@WebServlet("/StudentInsertServlet")
public class StudentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentOperationImp stuOper = new StudentOperationImp();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("welcome");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String no=request.getParameter("no");
		int i=0 ;
		if(StringUtils.isNullOrEmpty(no)) {///濡傛灉鏈塏o璇存槑鏄紪杈戯紝娌℃湁璇存槑鏄柊澧�
			Student s=new Student(0,name,Integer.parseInt(age));
			i=stuOper.insertStudent(s);
		}else {
			Student s=new Student(Integer.parseInt(no),name,Integer.parseInt(age));
			i=stuOper.updateStudent(s);
		}
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw=response.getWriter();
		pw.print(json);
		pw.close();
	}

}
