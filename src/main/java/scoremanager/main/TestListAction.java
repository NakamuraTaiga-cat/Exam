
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

        if (f4 != null && !f4.isBlank()) {

            dao.TestListStudentDao daoStudentList = new dao.TestListStudentDao();
            java.util.List<bean.TestListStudent> list =
                daoStudentList.filter(f4, teacher.getSchool());

            // 学生名を取得して画面に渡す
            dao.StudentDao studentDao = new dao.StudentDao();
            bean.Student student = studentDao.get(f4);

            request.setAttribute("student_list", list);
            request.setAttribute("searchType", "student");
            request.setAttribute("searched", true);
            request.setAttribute("f4", f4);
            request.setAttribute("student", student);

            request.getRequestDispatcher("test_list.jsp")
                   .forward(request, response);
            return;
        }


        // 入力チェック: 入学年度、クラス、科目が未選択（空文字またはブランク）の場合は
        // 検索画面に戻し、メッセージを表示する
        if (f1 == null || f2 == null || f3 == null
                || f1.isBlank() || f2.isBlank() || f3.isBlank()) {
            request.setAttribute("searched", false);
            request.setAttribute("errorMessage", "入学年度とクラスと科目を選択してください");
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

        // 該当データがない場合は検索画面へ戻してメッセージを表示する
        if (list == null || list.isEmpty()) {
            request.setAttribute("searched", false);
            request.setAttribute("errorMessage", "学生情報が存在しませんでした");
            request.getRequestDispatcher("test_list.jsp")
                   .forward(request, response);
            return;
        }

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
