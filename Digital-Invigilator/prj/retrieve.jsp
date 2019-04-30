<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.*"%>
<%@page import="java.net.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
//String id = request.getParameter("userid");
String driver = "com.mysql.cj.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "prj";
String userid = "root";
String password = "";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<body>

<h1>Retrieve data from database in jsp</h1>
<table border="1">
<tr>
<td>first name</td>
<td>last name</td>
<td>City name</td>


</tr>
<%
try{

connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="select rno,msg,photo from prj";
resultSet = statement.executeQuery(sql);
int cnt=0;

while(resultSet.next()){
cnt++;
Blob b=resultSet.getBlob(3);   //2 means 2nd column data
byte barr[]=b.getBytes(1,(int)b.length());   //1 means first image

FileOutputStream fout=new FileOutputStream("C:\\xampp\\tomcat\\webapps\\prj\\abc"+cnt+".jpeg");
fout.write(barr);

fout.close();


%>



<tr>
<td><%=resultSet.getString("rno") %></td>
<td><%=resultSet.getString("msg") %></td>
<td>  
    <img src="abc<%= cnt %>.jpeg" />  
</td> 

</tr>
<%

}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</body>
</html>