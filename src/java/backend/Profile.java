package backend;
import java.sql.*;
public class Profile {
    /** adds city and state of th student */
    public void updateAddress(String stu_id,String city,String state) throws SQLException, ClassNotFoundException{
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into student(city,state) values("+city+","+state+"where student_id="+stu_id+");";
            int x=stmt.executeUpdate(query);
            if(x==0)
                System.out.println("record not inserted");
    }
    /** adds current course being pursued by the student */
    public void addCourse(String s_id, String course) throws SQLException, ClassNotFoundException{
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into student(course) values("+course+"where student_id="+s_id+");";
            int x=stmt.executeUpdate(query);
            if(x==0)
                System.out.println("record not inserted");
    }
    /** adds designation of faculty, both for instructor and management */
    public void addDesig(String role, String id, String desig) throws SQLException, ClassNotFoundException{

            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = null;
            switch(role){
                case "Instructor": query = "insert into instructor(designation) values("+desig+");";
                                    break;
                case "Management": query = "insert into management(designation) values("+desig+");";
                                    break;
            }
            int x=stmt.executeUpdate(query);
            if(x==0)
                System.out.println("record not inserted");
    }
    public ResultSet getInfo(String role, String id) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query=null;
        switch(role){        
            case "Admin": query = "select * from admin where admin_id="+id;
            break;
            case "Management":query = "select * from  management where manage_id="+id;
            break;
            case "Student": query = "select * from student where student_id="+id;
            break;
            case "Instructor": query = "select * from instructor where instructor_id="+id;
        }
        ResultSet rs=stmt.executeQuery(query);
        return rs;
    }
    /** to search user's profile , STILL INCOMPLETE 
    public void searchProfile(String id, String search_name){
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query = "select ";
        int x=stmt.executeUpdate(query);
        if(x==0)
            System.out.println("record not inserted");
    } */
    
    /** query for above is select student_name as name from student where stu_name = "hi"  union
    select instructor_name as name from instructor where inst_name= "hi" union
    select manage_name as name from management where manage_name = "hi";
    */
}
