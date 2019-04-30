package bean;  
import java.sql.*;  
public class LoginDao {  
  
public static boolean validate(LoginBean beanobj){  
boolean status=false;  
try{  
	System.out.println(" test");
Connection con=ConnectionProvider.getCon();  
System.out.println(" test");              
PreparedStatement ps=con.prepareStatement("select * from login where email=? and pass=?");  
  System.out.println(" test");
  System.out.println(beanobj.getEmail());
  System.out.println(beanobj.getPass());
ps.setString(1,beanobj.getEmail());  
System.out.println(" test");
ps.setString(2, beanobj.getPass());  
System.out.println(" test");
ResultSet rs=ps.executeQuery();
System.out.println(" test6");  
status=rs.next();  
System.out.println(status);
System.out.println(" test7");
System.out.println("ResultSet: " + rs);
}catch(Exception e){ e.printStackTrace();}  
  
return status;  
  
}  
} 