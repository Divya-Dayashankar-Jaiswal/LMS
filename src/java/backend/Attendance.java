package backend;
import java.sql.*;

public class Attendance {
    public void updateAttend(String c_id,String s_id) throws SQLException, ClassNotFoundException{

            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "update classes set total_attendance=attendance+1 where class_id="+c_id+" and student_id="+s_id;
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("Attendance of "+s_id+" not added");
    }
    public void viewClassAttend(String c_id){
        //print class name
        //show list of students name & their total attendance
        //is this shown on new webpage or stored in an excel sheet?
    }
    public void viewStuAttend(String c_id,String s_id){
        //same ques as above
    }
    public void dailyAttend(String c_id,String s_id){
        //if daily attendance is required use this method
        //check if there's a way to get system's day to add in table daily_attendance
    }
}
