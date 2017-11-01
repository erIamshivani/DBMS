package jdbc;
import java.io.InputStream;
import java.io.BufferedReader;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;
import java.io.*;

public class jdbc {
	
	static final String db_url="jdbc:mysql://172.16.1.68:3306/b74";
	static final String user="b74";
	static final String pass="b74";
	PreparedStatement prep_stmt;

	public static <BufferedReader> void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection c=null;
		Statement st=null;
		int n;
		try{
		String query;
		String jdbc_drviver="com.mysql.jdbc.Driver";
		String db_url="jdbc:mysql://172.16.1.68:3306/b74";
		Class.forName(jdbc_drviver);
		c=DriverManager.getConnection(db_url,user,pass);
		st=c.createStatement();
		
		do{
			System.out.println("Menu:\n1.Insert\n2.Dispaly\n3.Update\n4.Delete\n\tEnter Choice:");
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
			int a=Integer.parseInt(((java.io.BufferedReader) b).readLine());
			
			switch(a)
			{
			case 1: System.out.println("\nInserting:\n");
					query="insert into emp(empno,ename,addr,phno,salary)"+"value(?,?,?,?,?)";
					System.out.println("Enter emp no.: ");
					int empno=Integer.parseInt(((java.io.BufferedReader) b).readLine());
					
					System.out.println("Enter emp no.: ");
					String name=((java.io.BufferedReader) b).readLine();
					
					System.out.println("Enter emp no.: ");
					String addr=((java.io.BufferedReader) b).readLine();
					
					System.out.println("Enter emp no.: ");
					int phno=Integer.parseInt(((java.io.BufferedReader) b).readLine());
					
					System.out.println("Enter emp no.: ");
					int salary=Integer.parseInt(((java.io.BufferedReader) b).readLine());
					
					PreparedStatement prep_stmt;
					prep_stmt=c.prepareStatement(query);
					prep_stmt.setInt(1, empno);
					prep_stmt.setString(2, name);
					prep_stmt.setString(3, addr);
					prep_stmt.setInt(4, phno);
					prep_stmt.setInt(5,salary);
					prep_stmt.execute();
					c.close();
					break;
					
			case 2: System.out.println("Database Contains:");
					query="select * from emp";
					int count=0;
					
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						empno=rs.getInt("empno");
						name=rs.getString("ename");
						addr=rs.getString("addr");
						phno=rs.getInt(phno);
						salary=rs.getInt(salary);
					
					String output="User#%d: %d - %d - %s - %d - %d";
					System.out.println(String.format(output, ++count,empno,name,addr,phno,salary));
					}
					c.close();
					break;
					
			case 4: System.out.println("\nDeleting:\nEnter empno to be deleted: ");
					empno=Integer.parseInt(((java.io.BufferedReader) b).readLine());
					query="delete from emp where empno=?";
					prep_stmt=c.prepareStatement(query);
					prep_stmt.setInt(1, empno);
					prep_stmt.execute();
					c.close();
					break;
			
			default:System.out.println("Wrong Input!");
			}
			System.out.println("Continue?");
			int n=Integer.parseInt(b.readLine());
		
		} while(n==1); 
		
	}
		catch(Exception e)
		{
			System.out.println(e);
		}

}
