package backend;
import java.sql.*;
public class manageClass {
    /** Adds new class to DB **/
    public void addClass(String id,String name,String desc,String instructor) throws SQLException, ClassNotFoundException{

            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "insert into classes(class_id,subject_name,description) values("+id+","+name+","+desc+");";
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("Class not added");
            updateInst(instructor,id);
    }
    /** Adds new/updated instructor of a class
     * @param inst_id
     * @param class_id **/
    public void updateInst(String inst_id,String class_id) throws SQLException, ClassNotFoundException{
            connectToDB conobject=new connectToDB();
            Statement stmt = conobject.getStatement();
            String query = "update classes set instructor="+inst_id+" where class_id="+class_id+";";
            int x=stmt.executeUpdate(query);
            //	if(x==0)
            //		errorbox("Instructor not inserted");
    }
    void addStudent(String class_id,String student_id){
        //add class & student in classes_transaction
        //is it necessary to have both the dates ?
    }
    /** Gets all students joined in a class
     * @param c_id
     * @return 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException */
    public ResultSet getStudents(String c_id) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        String query = "select ct.stu_name from classes_transaction ct,student s where ct.class_id="+c_id+" and ct.student_id=s.student_id";
        ResultSet rs=stmt.executeQuery(query);
        //now use this acc to need
        return rs;
    }
}
