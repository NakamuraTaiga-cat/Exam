package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {


    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

//         セッションからログインユーザー取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

//         リクエストパラメータ
        String entYearStr = request.getParameter("f1");
        String classNum   = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;

//         入学年度変換
        if (entYearStr != null && !entYearStr.equals("")) {
            entYear = Integer.parseInt(entYearStr);
        }

//         在学フラグ
        if (isAttendStr != null) {
            isAttend = true;
            request.setAttribute("f3", isAttendStr);
        }

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        List<Student> students;
        Map<String, String> errors = new HashMap<>();

//         ログインユーザーの学校コードをもとに暮らす番号の一覧を取得
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        boolean hasEntYear = entYear != 0;
        boolean hasClassNum = classNum != null && !classNum.equals("0");

//        学生一覧取得
        if (hasEntYear && hasClassNum) {
            // 入学年度＋クラス
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);

        } else if (hasEntYear) {
//             入学年度のみ
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);

        } else if (!hasEntYear && !hasClassNum) {
//             条件指定なし
            students = sDao.filter(teacher.getSchool(), isAttend);

        } else {
//             クラスのみ指定（エラー）
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            request.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

//        入学年度プルダウン用
        LocalDate today = LocalDate.now();
        int year = today.getYear();
//        リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
//        10年前から1年後まで年をリストに追加
        for (int i = year - 10; i <= year + 1; i++) {
            entYearSet.add(i);
        }

//        リクエストスコープにセット
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);

//        JSPへフォワード
        request.getRequestDispatcher("student_list.jsp").forward(request, response);
    }
}

