package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");


        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        int entYear = Integer.parseInt(req.getParameter("ent-year"));
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("class-num");


        	req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);

 
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        StudentDao dao = new StudentDao();
        boolean result = dao.save(student);

        if (result) {
            req.getRequestDispatcher("/scoremanager/main/student_create_done.jsp")
               .forward(req, res);
        } else {
            // エラー時：入力保持
            req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);

            new StudentCreateAction().execute(req, res);
        }
    }
}
