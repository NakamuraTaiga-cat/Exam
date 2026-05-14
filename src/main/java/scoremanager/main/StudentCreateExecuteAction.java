package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
import java.util.HashMap;
import java.util.Map;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = req.getParameter("ent-year");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("class-num");

        Map<String, String> errors = new HashMap<>();

        // バリデーション
        int entYear = 0;
        if (entYearStr == null || entYearStr.equals("0") || entYearStr.equals("")) {
            errors.put("ent_year", "入学年度を選択してください");
        } else {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("ent_year", "入学年度を選択してください");
            }
        }

        if (no == null || no.trim().equals("")) {
            errors.put("no", "このフィールドを入力してください");
        }
        if (name == null || name.trim().equals("")) {
            errors.put("name", "このフィールドを入力してください");
        }

        StudentDao dao = new StudentDao();
        // 学生番号の重複チェック（新規登録時のみ）
        if (!errors.containsKey("no") && no != null && !no.trim().equals("")) {
            Student existing = dao.get(no);
            if (existing != null) {
                errors.put("no", "学生番号が重複しています");
            }
        }

        // 入力エラーがあれば再表示（入力値を保持）
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            if (entYear != 0) req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);

            // StudentCreateActionでプルダウン等を準備して画面表示
            new StudentCreateAction().execute(req, res);
            return;
        }

        // 保存処理
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        boolean result = dao.save(student);

        if (result) {
            req.getRequestDispatcher("/scoremanager/main/student_create_done.jsp").forward(req, res);
        } else {
            // 想定外のエラー時はエラーメッセージをセットして再表示
            errors.put("general", "登録に失敗しました。もう一度お試しください。");
            req.setAttribute("errors", errors);
            req.setAttribute("ent_year", entYear);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            new StudentCreateAction().execute(req, res);
        }
    }
}
