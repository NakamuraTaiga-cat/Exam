package scoremanager.main;

import java.util.List;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {

    public void execute(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        SubjectDao dao = new SubjectDao();

        List<Subject> list = dao.filter();

        request.setAttribute("list", list);
        
        request.getRequestDispatcher(
        	    "/scoremanager/main/subject_list.jsp"
        		
        ).forward(request, response);
    }
}