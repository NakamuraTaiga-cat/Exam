package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    public void execute(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        String cd = request.getParameter("cd");

        SubjectDao dao = new SubjectDao();

        Subject subject = dao.get(cd);

        request.setAttribute(
            "subject",
            subject
        );

        request.getRequestDispatcher(
            "/scoremanager/main/subject_delete.jsp"
        ).forward(request, response);
    }
}