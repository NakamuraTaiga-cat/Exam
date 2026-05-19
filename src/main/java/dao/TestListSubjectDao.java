
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	public List<TestListSubject> filter(
	        int entYear,
	        String classNum,
	        String subjectCd,
	        School school) throws Exception {
	
	    List<TestListSubject> list = new ArrayList<>();
	    Connection con = getConnection();
	
	    String sql =
	        "SELECT s.ent_year, s.class_num, s.no AS student_no, s.name AS student_name, " +
	        "       t.no AS test_no, t.point " +
	        "FROM student s " +
	        "LEFT JOIN test t " +
	        "  ON s.no = t.student_no " +
	        " AND t.subject_cd = ? " +
	        " AND t.school_cd = ? " +
	        "WHERE s.school_cd = ? " +
	        "  AND s.ent_year = ? " +
	        "  AND s.class_num = ? " +
	        "ORDER BY s.no, t.no";
	
	    PreparedStatement ps = con.prepareStatement(sql);

	    ps.setString(1, subjectCd);
	    ps.setString(2, school.getCd());
	    ps.setString(3, school.getCd());
	    ps.setInt(4, entYear);
	    ps.setString(5, classNum);
	
	    ResultSet rs = ps.executeQuery();
	
	    String currentStudentNo = null;
	    TestListSubject current = null;
	
	    while (rs.next()) {


	        String studentNo = rs.getString("student_no");
	
	        if (!studentNo.equals(currentStudentNo)) {
	            current = new TestListSubject();
	            current.setEntYear(rs.getInt("ent_year"));
	            current.setClassNum(rs.getString("class_num"));
	            current.setStudentNo(studentNo);
	            current.setStudentName(rs.getString("student_name"));
	            current.setPointsMap(new HashMap<>());
	
	            list.add(current);
	            currentStudentNo = studentNo;
	        }
	
	        int testNo = rs.getInt("test_no");
	        if (!rs.wasNull()) {

			current.getPointsMap().put(
			    String.valueOf(testNo),
			    rs.getInt("point")
			);

	        }
	    }
	
	    rs.close();
	    ps.close();
	    con.close();
	
	    return list;
	}
}