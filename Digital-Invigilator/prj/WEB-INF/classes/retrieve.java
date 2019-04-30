package bean;  
import java.sql.*;  
public class retrieve {  
  
public static boolean validate()
{  
	boolean status=false;  
try{  


Connection con=ConnectionProvider.getCon();  
        

PreparedStatement ps=con.prepareStatement("select * from login "); 


  System.out.println(beanobj.getEmail());
  System.out.println(beanobj.getPass());

ps.setString(1,beanobj.getEmail());  



ps.setString(2, beanobj.getPass());


ResultSet rs=ps.executeQuery();
ResultSetMetaData rsmd = rs.getMetaData();
int columnsNumber = rsmd.getColumnCount();

ResultSetMetaData rsmd = rs.getMetaData();
int columnsNumber = rsmd.getColumnCount();                     


while (rs.next()) {
/        
for(int i = 1 ; i <= columnsNumber; i++){

      System.out.print(rs.getString(i) + " "); 

}

  System.out.println();           

    }


 
status=rs.next();  
System.out.println(status);

System.out.println("ResultSet: " + rs);
}catch(Exception e){ e.printStackTrace();}  
  
return status;  
  
}  
} 