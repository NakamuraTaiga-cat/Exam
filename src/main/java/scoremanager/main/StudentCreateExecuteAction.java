package scoremanager.main;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
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

        StudentDao dao = new StudentDao();
        if (dao.get(no) != null) {

        	
            // エラーメッセージ
            req.setAttribute("error_no", "学生番号が重複しています");

            // 入力値を保持
            School school = teacher.getSchool();

    		List<Integer> entYearSet = new ArrayList<>();
    	    int currentYear = Year.now().getValue();
    	    for (int i = currentYear; i >= currentYear - 10; i--) {
    	        entYearSet.add(i);
    	    }
    		
            ClassNumDao classDao = new ClassNumDao();
            List<String> classList = classDao.filter(school);
            req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            req.setAttribute("ent_year_set", entYearSet);
            req.setAttribute("class_list", classList);

            // 入力画面に戻る
            req.getRequestDispatcher("student_create.jsp")
               .forward(req, res);
            return;
        }

 
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        
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
