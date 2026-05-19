package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public List<TestListStudent> filter(String studentNo, School school) throws Exception {

        List<TestListStudent> list = new ArrayList<>();
        Connection con = getConnection();

        String sql =
            "SELECT t.no AS test_no, t.point, s.cd AS subject_cd, s.name AS subject_name " +
            "FROM test t " +
            "JOIN subject s ON t.subject_cd = s.cd AND s.school_cd = t.school_cd " +
            "WHERE t.student_no = ? " +
            "  AND t.school_cd = ? " +
            "ORDER BY s.cd, t.no";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, studentNo);
        ps.setString(2, school.getCd());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            TestListStudent t = new TestListStudent();
            t.setSubjectCd(rs.getString("subject_cd"));
            t.setSubjectName(rs.getString("subject_name"));
            t.setNum(rs.getInt("test_no"));

            int p = rs.getInt("point");
            if (rs.wasNull()) {
                t.setPoint(null);
            } else {
                t.setPoint(p);
            }

            list.add(t);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }
}
