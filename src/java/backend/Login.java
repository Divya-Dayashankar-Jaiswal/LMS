package backend;
import java.sql.*;

public class Login {
    public int verify(String id,String pswrd,String role) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query= null;
        switch(role){
            case "Student":query="select exists(select * from student where student_id="+id+" and password="+pswrd+");";
			break;
            case "Instructor":query="select exists(select * from instructor where instructor_id="+id+" and password="+pswrd+");";
			break;
            case "Admin":query="select exists(select * from admin where admin_id="+id+" and password="+pswrd+");";
                        break;
            case "Management":query="select exists(select * from management where manage_id="+id+" and password="+pswrd+");";
                        break;
        }
        return stmt.executeUpdate(query);
    }
    /** updates password for "Forgot Password" or "Change password"  */
    void forgotPass(String id,String role,String pswrd) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query="";
        switch(role){
            case "Student":query="update student set password="+pswrd+" where stu_id="+id+");";
			break;
            case "Instructor":query="update instructor set password="+pswrd+" where instructor_id="+id+");";
			break;
            case "Admin":query="update admin set password="+pswrd+" where admin_id="+id+");";
                        break;
            case "Management":query="update management set password="+pswrd+" where manage_id="+id+");";
                        break;
        }
        stmt.executeUpdate(query);
    }
}
