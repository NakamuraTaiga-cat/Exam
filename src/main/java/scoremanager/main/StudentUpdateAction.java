package scoremanager.main;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		String no = request.getParameter("no");

		StudentDao dao = new StudentDao();
		Student student = dao.get(no);

		if (student == null) {
			// 存在しない場合は一覧に戻す
			request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);
			return;
		}

		// 入学年度プルダウン
		List<Integer> entYearSet = new ArrayList<>();
		int currentYear = Year.now().getValue();
		for (int i = currentYear; i >= currentYear - 10; i--) {
			entYearSet.add(i);
		}

		ClassNumDao classDao = new ClassNumDao();
		List<String> classList = classDao.filter(teacher.getSchool());

		// リクエストスコープにセット
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_list", classList);

		// 学生情報の初期値をセット
		request.setAttribute("ent_year", student.getEntYear());
		request.setAttribute("no", student.getNo());
		request.setAttribute("name", student.getName());
		request.setAttribute("class_num", student.getClassNum());
		request.setAttribute("is_attend", student.isAttend());

		request.getRequestDispatcher("/scoremanager/main/student_update.jsp").forward(request, response);
	}

}
