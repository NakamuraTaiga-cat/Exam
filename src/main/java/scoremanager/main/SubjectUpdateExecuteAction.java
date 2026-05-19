package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    public void execute(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        boolean hasError = false;

        SubjectDao dao = new SubjectDao();

        // 科目存在チェック
        if (dao.get(cd) == null) {

            request.setAttribute(
                "cdError",
                "科目が存在していません"
            );

            hasError = true;
        }

        // 科目名未入力チェック
        if (name == null || name.isEmpty()) {

            request.setAttribute(
                "nameError",
                "⚠ このフィールドを入力してください。"
            );

            hasError = true;
        }

        request.setAttribute("name", name);

        // エラー時
        if (hasError) {

            Subject subject = new Subject();

            subject.setCd(cd);
            subject.setName(name);

            request.setAttribute(
                "subject",
                subject
            );

            request.getRequestDispatcher(
                "/scoremanager/main/subject_update.jsp"
            ).forward(request, response);

            return;
        }

        // 更新
        Subject subject = new Subject();

        subject.setSchoolCd("TIC");
        subject.setCd(cd);
        subject.setName(name);

        dao.update(subject);

        request.getRequestDispatcher(
            "/scoremanager/main/subject_update_done.jsp"
        ).forward(request, response);
    }
}