package backend;
import java.sql.*;
public class connectToDB {
    /** default constructor */
    public connectToDB(){}
    /** provides connection with database */
    public Connection connect() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver"); //mySQL driver
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root","scottlang");
		return con;
    }
    /** creates statement for sql */
    public Statement getStatement() throws SQLException, ClassNotFoundException{
        Connection con=connect();
        Statement stmt = con.createStatement();
        return stmt;
    }
}
