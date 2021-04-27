package backend;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class toDo {
    public void addItem(String user_id,String item) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        stmt.executeUpdate("insert into todo values("+user_id+","+item+");");
    }
    public void deleteItem(String user_id, String item) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        stmt.executeUpdate("delete from todo where user_id="+user_id+" and item="+item+");");
    }
    public ResultSet viewItems(String user_id) throws SQLException, ClassNotFoundException{
        connectToDB conobject=new connectToDB();
        Statement stmt = conobject.getStatement();
        ResultSet rs = stmt.executeQuery("select * from todo where user_id="+user_id+");");
        return rs;
    }
}
