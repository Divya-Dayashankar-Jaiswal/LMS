package backend;
import java.sql.*;

public class Register{
    /** add new user according to role, should be called for adding new user, no need to call other functions */
    //added String code in parameters
    //sent it in function calls
    public void NewUser(String college_id,String name, String email,String role,String pswrd) throws SQLException, ClassNotFoundException{
	switch(role){
            case "Student":regStu(college_id,name,email,pswrd);
			break;
            case "Instructor":regInst(college_id,name,email,pswrd);
			break;
            case "Admin":regAdmin(college_id,name,email,pswrd);
                        break;
            case "Management":regManage(college_id,name,email,pswrd);
                        break;
	}
    }
    /** add new student */ //added string code in parameters
   void regStu(String id,String name, String email,String pswrd) throws SQLException, ClassNotFoundException{
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into student(student_id,stu_name,email,password) values("+id+","+name+","+email+","+pswrd+");";
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("record not inserted");
    }
    /** add new instructor **/
    void regInst(String id,String name, String email,String pswrd) throws SQLException, ClassNotFoundException{

            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into instructor(instructor_id,inst_name,email,password) values("+id+","+name+","+email+","+pswrd+");";
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("record not inserted");
    }
    /** Add new admin */
    void regAdmin(String id,String name, String email,String password) throws SQLException, ClassNotFoundException{

            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into admin values("+id+","+name+","+email+","+password+");";
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("record not inserted");
    }
    /** Adds new management faculty */
    void regManage(String id,String name, String email, String pswrd) throws SQLException, ClassNotFoundException{
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into management(manage_id,manage_name,email,password) values("+id+","+name+","+email+","+pswrd+");";
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("record not inserted");
    }
}