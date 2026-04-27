package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao {

    public List<String> filter(School school) throws Exception {

        List<String> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT class_num " +
            "FROM student " +
            "WHERE school_cd = ? " +
            "ORDER BY class_num"
        );

        // 学校コードをセット
        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        // クラス番号を1件ずつリストに追加
        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }

        st.close();
        con.close();

        return list;
    }
}