package backend;
import java.sql.*;

public class Marks {
    /** Add marks to a student's existing marks of a particular class */
    void addMarks(String c_id,String s_id,float marks) throws SQLException, ClassNotFoundException{
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "update marks set total_marks=total_marks+marks where class_id="+c_id+" and student_id="+s_id;
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("Marks of "+s_id+" not added");
    }
    /** View students' marks from a class */
    ResultSet viewClassMarks(String c_id) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query = "select m.student_id as ID,s.stu_name as Name,m.total_marks as Total Marks from student s,marks m where class_id="+c_id+" and s.student_id=m.student_id";
        ResultSet rs=stmt.executeQuery(query);
        //now use this acc to need
        return rs;
    }
    /** View a student's marks from all classes */
    ResultSet viewStuMarks(String s_id) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query = "select c.class_id as Course Code,c.subject_name as Subject,m.total_marks as Total Marks from classes c,marks m where student_id="+s_id+" and c.class_id=m.class_id";
        ResultSet rs=stmt.executeQuery(query);
        //now use this acc to need
        return rs;
    }
}
