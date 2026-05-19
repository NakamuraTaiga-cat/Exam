package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import bean.Util;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;



public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        Teacher teacher = Util.getUser(request);
        School school = teacher.getSchool();

        Util.setEntYearSet(request);
        Util.setClassNumSet(request);
        Util.setSubjectSet(request);
        Util.setNumSet(request);

        String f1 = request.getParameter("f1"); // 入学年度
        String f2 = request.getParameter("f2"); // クラス
        String f3 = request.getParameter("f3"); // 科目CD
        String f4 = request.getParameter("f4"); // 回数



		if (f1 != null) {
		
		    // 全項目が選択されている場合
		    if (
		        !"0".equals(f1) &&
		        !"0".equals(f2) &&
		        !"0".equals(f3) &&
		        !"0".equals(f4)
		    ) {
		
		        int entYear = Integer.parseInt(f1);
		        String classNum = f2;
		        int testNo = Integer.parseInt(f4);
		
		        Subject subject = new Subject();
		        
		        subject.setCd(f3);
		        
		
		        TestDao dao = new TestDao();
		        List<Test> testList =
		                dao.filter(entYear, classNum, subject, testNo, school);

		        request.setAttribute("searched", true);
		        request.setAttribute("test_list", testList);
		        request.setAttribute("subject", subject);
		        request.setAttribute("test_no", testNo);
		
		    } else {
		        // 検索押下後だが未選択あり
		        request.setAttribute(
		            "message",
		            "入学年度とクラスと科目と回数を選択してください"
		        );
		    }


		    request.setAttribute("f1", f1);
		    request.setAttribute("f2", f2);
		    request.setAttribute("f3", f3);
		    request.setAttribute("f4", f4);

		}


        request.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
               .forward(request, response);
    }
}
