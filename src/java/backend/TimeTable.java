package backend;
import java.sql.*;

public class TimeTable {
    /** Add a class' schedule to timetable */
    void updateTT(String day,String class_id,String name,String timings) throws SQLException, ClassNotFoundException{
        //time format must be specified on webpage so that it can be directly inserted

            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into timetable values("+day+","+class_id+","+name+","+timings+");";
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("Timetable not updated");

    }
    ResultSet getTT(String day,String s_id) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query = "select class_name,timings from timetable where day="+day+" and class_id in (select class_id from classes_transaction where student_id="+s_id+");";
        ResultSet rs=stmt.executeQuery(query);
        //now use this acc to need
        return rs;
    }
}