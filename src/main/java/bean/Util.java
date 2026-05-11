package bean;

import java.util.ArrayList;
import java.util.List;

import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import scoremanager.main.SubjectDao;

public class Util {

    public static Teacher getUser(HttpServletRequest request) {
        return (Teacher) request.getSession().getAttribute("user");
    }


    public static void setEntYearSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        School school = teacher.getSchool();

        StudentDao studentDao = new StudentDao();
        List<Integer> entYearSet = studentDao.getEntYearList(school);

        request.setAttribute("ent_year_set", entYearSet);
    }

    public static void setClassNumSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        School school = teacher.getSchool();

        ClassNumDao classNumDao = new ClassNumDao();
        request.setAttribute("class_num_set", classNumDao.filter(school));
    }

    public static void setSubjectSet(HttpServletRequest request) throws Exception {
        Teacher teacher = getUser(request);
        School school = teacher.getSchool();

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectSet = subjectDao.filterBySchool(school);

        request.setAttribute("subject_set", subjectSet);
    }


	public static void setNumSet(HttpServletRequest request) {
	    List<Integer> list = new ArrayList<>();
	    for (int i = 1; i <= 2; i++) {
	        list.add(i);
	    }
	    request.setAttribute("num_set", list);
	}

}

