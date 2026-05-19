package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {

    public void execute(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        boolean hasError = false;

        if (cd == null || cd.isEmpty()) {

            request.setAttribute(
                "cdError",
                "⚠ このフィールドを入力してください。"
            );

            hasError = true;
        }

        if (name == null || name.isEmpty()) {

            request.setAttribute(
                "nameError",
                "⚠ このフィールドを入力してください。"
            );

            hasError = true;
        }

        if (cd != null &&
            !cd.isEmpty() &&
            cd.length() != 3) {

            request.setAttribute(
                "cdError",
                "科目コードは3文字で入力してください"
            );

            hasError = true;
        }

        SubjectDao dao = new SubjectDao();

        if (cd != null &&
            !cd.isEmpty() &&
            dao.get(cd) != null) {

            request.setAttribute(
                "cdError",
                "科目コードが重複しています"
            );

            hasError = true;
        }

        request.setAttribute("name", name);

        if (hasError) {

            request.getRequestDispatcher(
                "/scoremanager/main/subject_create.jsp"
            ).forward(request, response);

            return;
        }
        
        Subject subject = new Subject();

        subject.setSchoolCd("TIC");
        subject.setCd(cd);
        subject.setName(name);

        dao.save(subject);

        request.getRequestDispatcher(
            "/scoremanager/main/subject_create_done.jsp"
        ).forward(request, response);
    }
}