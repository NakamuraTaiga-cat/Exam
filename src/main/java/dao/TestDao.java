package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    public List<Test> filter(int entYear,String classNum,Subject subject,int testNo,School school) throws Exception {

        List<Test> list = new ArrayList<>();
        Connection con = getConnection();


        PreparedStatement ps = con.prepareStatement(
        		"SELECT s.ent_year, s.class_num, s.no, s.name, t.point " +
                "FROM student s " +
                "LEFT JOIN test t " +
                "ON s.no = t.student_no " +
                "AND t.subject_cd = ? AND t.no = ? " +
                "WHERE s.school_cd = ? " +
                "AND s.ent_year = ? " +
                "AND s.class_num = ? " +
                "ORDER BY s.no"
                );
        ps.setString(1, subject.getCd());
        ps.setInt(2, testNo);
        ps.setString(3, school.getCd());
        ps.setInt(4, entYear);
        ps.setString(5, classNum);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

			Student student = new Student();
			student.setNo(rs.getString("no"));
			student.setName(rs.getString("name"));
			student.setEntYear(rs.getInt("ent_year"));
			student.setClassNum(rs.getString("class_num"));
			student.setSchool(school);
			
			Test test = new Test();
			test.setStudent(student);
			test.setSubject(subject);
			test.setNo(testNo);
			int p = rs.getInt("point");
			if (rs.wasNull()) {
			    test.setPoint(null);
			} else {
			    test.setPoint(p);
			}
			test.setSchool(school);
			
			list.add(test);

        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }


	public boolean save(List<Test> list) throws Exception {
	
	    if (list == null || list.isEmpty()) {
	        return false;
	    }
	
	    Connection con = getConnection();
	    con.setAutoCommit(false);
	
	    boolean result = true;
	
	    String checkSql =
	        "SELECT COUNT(*) FROM test " +
	        "WHERE student_no = ? AND subject_cd = ? AND no = ? AND school_cd = ?";
	
	    String insertSql =
	        "INSERT INTO test(student_no, subject_cd, school_cd, no, point, class_num) " +
	        "VALUES (?, ?, ?, ?, ?, ?)";
	
	    String updateSql =
	    		"UPDATE test SET point = ?, class_num = ? " +
	    		"WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";

	    try {
	        for (Test test : list) {
	
	            PreparedStatement checkPs = con.prepareStatement(checkSql);
	            checkPs.setString(1, test.getStudent().getNo());
	            checkPs.setString(2, test.getSubject().getCd());
	            checkPs.setInt(3, test.getNo());
	            checkPs.setString(4, test.getSchool().getCd());
	
	            ResultSet rs = checkPs.executeQuery();
	            rs.next();
	            boolean exists = rs.getInt(1) > 0;
	
	            rs.close();
	            checkPs.close();
	
	            PreparedStatement ps;

				if (exists) {
				    ps = con.prepareStatement(updateSql);
					ps.setInt(1, test.getPoint());
					ps.setString(2, test.getStudent().getClassNum());
					ps.setString(3, test.getStudent().getNo());
					ps.setString(4, test.getSubject().getCd());
					ps.setString(5, test.getSchool().getCd());
					ps.setInt(6, test.getNo());
				} else {
				    ps = con.prepareStatement(insertSql);
					ps.setString(1, test.getStudent().getNo());
					ps.setString(2, test.getSubject().getCd());
					ps.setString(3, test.getSchool().getCd());
					ps.setInt(4, test.getNo());
					ps.setInt(5, test.getPoint());
				    ps.setString(6, test.getStudent().getClassNum());
				}

	
	            if (ps.executeUpdate() != 1) {
	                result = false;
	            }
	
	            ps.close();
	        }
	
	        con.commit();
	
	    } catch (Exception e) {
	        con.rollback();
	        throw e;
	
	    } finally {
	        con.close();
	    }
	
	    return result;
	}

}
