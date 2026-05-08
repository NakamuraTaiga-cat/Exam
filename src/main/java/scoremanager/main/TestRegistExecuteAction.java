package scoremanager.main;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import bean.Util;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;




public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Teacher teacher = Util.getUser(request);
        School school = teacher.getSchool();

        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        String f4 = request.getParameter("f4");

        if (f3 == null || f4 == null) {
            response.sendRedirect("TestRegist.action");
            return;
        }

        Subject subject = new Subject();
        subject.setCd(f3);
        int testNo = Integer.parseInt(f4);

        Map<String, String> errors = new HashMap<>();
        List<Test> saveList = new ArrayList<>();

        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();

            if (!name.startsWith("point_")) continue;

            String studentNo = name.replace("point_", "");
            String value = request.getParameter(name);

            if (value == null || value.isEmpty()) continue;

            int point;
            try {
                point = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                errors.put(name, "0～100の範囲で入力してください");
                continue;
            }

            if (point < 0 || point > 100) {
                errors.put(name, "0～100の範囲で入力してください");
                continue;
            }

            Student student = new Student();
            student.setNo(studentNo);
            student.setClassNum(f2);
            student.setSchool(school);

            Test test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setNo(testNo);
            test.setPoint(point);
            test.setSchool(school);
            

            saveList.add(test);
        }

        if (saveList.isEmpty() && errors.isEmpty()) {

            Util.setEntYearSet(request);
            Util.setClassNumSet(request);
            Util.setSubjectSet(request);
            Util.setNumSet(request);

            request.setAttribute("searched", true);
            request.setAttribute("f3", f3);
            request.setAttribute("f4", f4);
            request.setAttribute("message", "点数が入力されていません");

            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
                   .forward(request, response);
            return;
        }

        if (!errors.isEmpty()) {

            Util.setEntYearSet(request);
            Util.setClassNumSet(request);
            Util.setSubjectSet(request);
            Util.setNumSet(request);

            request.setAttribute("searched", true);
            request.setAttribute("errors", errors);
            request.setAttribute("f2", f2);
            request.setAttribute("f3", f3);
            request.setAttribute("f4", f4);
            request.setAttribute("test_list", saveList);

            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
                   .forward(request, response);
            return;
        }

        TestDao dao = new TestDao();
        dao.save(saveList);

        request.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
               .forward(request, response);
    }
}


