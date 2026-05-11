
package scoremanager.main;

import java.util.List;

import bean.Teacher;
import bean.TestListSubject;
import bean.Util;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

        // ログインユーザー
        Teacher teacher = Util.getUser(request);

        // 初期画面でも必須
        Util.setEntYearSet(request);
        Util.setClassNumSet(request);
        Util.setSubjectSet(request);

        // パラメータ取得
        String f1 = request.getParameter("f1"); // 入学年度
        String f2 = request.getParameter("f2"); // クラス
        String f3 = request.getParameter("f3"); // 科目
        String f4 = request.getParameter("f4"); // 学生番号

        /* ===== 初期画面 ===== */
        if (f1 == null && f2 == null && f3 == null && f4 == null) {
            request.setAttribute("searched", false);
            request.getRequestDispatcher("test_list.jsp")
                   .forward(request, response);
            return;
        }

//        if (f4 != null && !f4.isBlank()) {
//
//            TestListStudentDao dao = new TestListStudentDao();
//            List<TestListStudent> list =
//                dao.filter(f4, teacher.getSchool());
//
//            request.setAttribute("student_list", list);
//            request.setAttribute("searchType", "student");
//            request.setAttribute("searched", true);
//            request.setAttribute("f4", f4);
//
//            request.getRequestDispatcher("test_list.jsp")
//                   .forward(request, response);
//            return;
//        }


        if ("0".equals(f1) || "0".equals(f2) || "0".equals(f3)) {
            request.setAttribute("searched", false);
            request.getRequestDispatcher("test_list.jsp")
                   .forward(request, response);
            return;
        }

        int entYear = Integer.parseInt(f1);
        String classNum = f2;
        String subjectCd = f3;

        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> list =
            dao.filter(entYear, classNum, subjectCd, teacher.getSchool());

        request.setAttribute("test_list", list);
        request.setAttribute("searchType", "subject");
        request.setAttribute("searched", true);

        // 条件保持
        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);

        
        request.getRequestDispatcher("test_list.jsp")
               .forward(request, response);
    }
}
