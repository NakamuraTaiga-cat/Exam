package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    public List<Subject> filter() throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st =
            con.prepareStatement(
                "select * from subject"
            );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Subject s = new Subject();

            s.setSchoolCd(
                rs.getString("school_cd")
            );

            s.setCd(
                rs.getString("cd")
            );

            s.setName(
                rs.getString("name")
            );

            list.add(s);
        }

        st.close();
        con.close();

        return list;
    }

    public boolean save(Subject subject) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "insert into subject values (?, ?, ?)"
        );

        st.setString(1, subject.getSchoolCd());
        st.setString(2, subject.getCd());
        st.setString(3, subject.getName());

        int count = st.executeUpdate();

        st.close();
        con.close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public Subject get(String cd) throws Exception {

        Subject subject = new Subject();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "select * from subject where cd=?"
        );

        st.setString(1, cd);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {

            subject.setSchoolCd(
                rs.getString("school_cd")
            );

            subject.setCd(
                rs.getString("cd")
            );

            subject.setName(
                rs.getString("name")
            );

        } else {
            subject = null;
        }

        st.close();
        con.close();

        return subject;
    }
    
    public boolean update(Subject subject)
    		throws Exception {

    		    Connection con = getConnection();

    		    PreparedStatement st = con.prepareStatement(
    		        "update subject set name=? where cd=?"
    		    );

    		    st.setString(1, subject.getName());
    		    st.setString(2, subject.getCd());

    		    int count = st.executeUpdate();

    		    st.close();
    		    con.close();

    		    if (count > 0) {
    		        return true;
    		    } else {
    		        return false;
    		    }
    		}
    
    public boolean delete(String cd)
    		throws Exception {

    		    Connection con = getConnection();

    		    PreparedStatement st = con.prepareStatement(
    		        "delete from subject where cd=?"
    		    );

    		    st.setString(1, cd);

    		    int count = st.executeUpdate();

    		    st.close();
    		    con.close();

    		    if (count > 0) {
    		        return true;
    		    } else {
    		        return false;
    		    }
    		}
    
    
  
}