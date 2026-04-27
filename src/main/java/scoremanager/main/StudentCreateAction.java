package scoremanager.main;

import bean.Student;
import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		      セッションからログインユーザー取得
		 HttpSession session = request.getSession();
		 Teacher teacher = (Teacher)session.getAttribute("user");
		
//		      リクエストパラメータ
		 String entYearStr = request.getParameter("ent-year");
		 String noStr   = request.getParameter("no");
		 String nameStr   = request.getParameter("name");
		 String classNumStr = request.getParameter("class-num");
		 
			int entYear = Integer.parseInt(entYearStr);
			
			Student student = new Student();
			student.setEntYear(entYear);
			student.setNo(noStr);
			student.setName(nameStr);
			student.setClassNum(classNumStr);
			student.setSchool(teacher.getSchool());

		 
     
     
     
     
	}
	
}
