package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		String entYearStr = req.getParameter("ent-year");
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classNum = req.getParameter("class-num");
		String isAttendParam = req.getParameter("is-attend");

		Map<String, String> errors = new HashMap<>();

		int entYear = 0;
		if (entYearStr != null && !entYearStr.equals("")) {
			try {
				entYear = Integer.parseInt(entYearStr);
			} catch (NumberFormatException e) {
				// ignore, keep 0
			}
		}

		if (name == null || name.trim().equals("")) {
			errors.put("name", "このフィールドを入力してください");
		}

		StudentDao dao = new StudentDao();

		if (!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			if (entYear != 0) req.setAttribute("ent_year", entYear);
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			req.setAttribute("is_attend", isAttendParam != null);

			new StudentUpdateAction().execute(req, res);
			return;
		}

		Student student = new Student();
		student.setEntYear(entYear);
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setAttend(isAttendParam != null);
		student.setSchool(teacher.getSchool());

		boolean result = dao.save(student);

		if (result) {
			req.getRequestDispatcher("/scoremanager/main/student_update_done.jsp").forward(req, res);
		} else {
			errors.put("general", "更新に失敗しました。もう一度お試しください。");
			req.setAttribute("errors", errors);
			req.setAttribute("ent_year", entYear);
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			req.setAttribute("is_attend", isAttendParam != null);
			new StudentUpdateAction().execute(req, res);
		}
	}

}
