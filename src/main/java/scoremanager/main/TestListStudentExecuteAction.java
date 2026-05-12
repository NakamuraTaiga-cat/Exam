package scoremanager.main;

import java.util.List;

import bean.TestListStudent;
import bean.Teacher;
import bean.Util;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Teacher teacher = Util.getUser(request);

		String f4 = request.getParameter("f4"); // 学生番号

		if (f4 == null || f4.isBlank()) {
			// 条件が不正な場合は一覧画面に戻す
			Util.setEntYearSet(request);
			Util.setClassNumSet(request);
			Util.setSubjectSet(request);

			request.setAttribute("searched", false);
			request.getRequestDispatcher("test_list.jsp").forward(request, response);
			return;
		}

		TestListStudentDao dao = new TestListStudentDao();
		List<TestListStudent> list = dao.filter(f4, teacher.getSchool());

		// 学生名を取得して画面に渡す
		dao.StudentDao studentDao = new dao.StudentDao();
		bean.Student student = studentDao.get(f4);

		request.setAttribute("student_list", list);
		request.setAttribute("searchType", "student");
		request.setAttribute("searched", true);
		request.setAttribute("f4", f4);
		request.setAttribute("student", student);

		request.getRequestDispatcher("test_list.jsp").forward(request, response);
	}
}
