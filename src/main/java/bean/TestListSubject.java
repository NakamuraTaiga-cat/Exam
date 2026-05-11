
package bean;

import java.util.Map;

public class TestListSubject {

    private int entYear;
    private String classNum;
    private String studentNo;
    private String studentName;
    private Map<String, Integer> pointsMap;

    public int getEntYear() {
        return entYear;
    }
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Map<String, Integer> getPointsMap() {
        return pointsMap;
    }
    public void setPointsMap(Map<String, Integer> pointsMap) {
        this.pointsMap = pointsMap;
    }
}
