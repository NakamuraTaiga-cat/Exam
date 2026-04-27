package scoremanager.main;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		      セッションからログインユーザー取得
		 HttpSession session = request.getSession();
		 Teacher teacher = (Teacher)session.getAttribute("user");
		
		 School school = teacher.getSchool();

		List<Integer> entYearSet = new ArrayList<>();
	    int currentYear = Year.now().getValue();
	    for (int i = currentYear; i >= currentYear - 10; i--) {
	        entYearSet.add(i);
	    }
		

	    ClassNumDao classDao = new ClassNumDao();
        List<String> classList = classDao.filter(school);


		request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_list", classList);

        /* 画面表示 */
        request.getRequestDispatcher("/scoremanager/main/student_create.jsp")
           .forward(request, response);

     
     
     
     
	}
	
}
