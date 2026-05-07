package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import dao.Dao;

public class SubjectDao extends Dao {

    /**
     * 学校に属する科目一覧を取得
     */
    public List<Subject> filterBySchool(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT cd, name " +
            "FROM subject " +
            "WHERE school_cd = ? " +
            "ORDER BY cd"
        );

        ps.setString(1, school.getCd());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subject.setSchool(school);
            list.add(subject);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }
}
