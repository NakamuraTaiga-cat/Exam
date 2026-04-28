package scoremanager.main;

import bean.School;
import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

//      セッションからログインユーザー取得
     HttpSession session = request.getSession();
     Teacher teacher = (Teacher) session.getAttribute("user");
     
     School school = teacher.getSchool();
     
     
     
     request.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
     .forward(request, response);

	}
}
