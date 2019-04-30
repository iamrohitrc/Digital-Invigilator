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


<!doctype html>
<html lang="en">

<head>
  <title>Total Surviellence</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- Material Kit CSS -->
  <link href="assets/css/material-kit.css?v=2.0.4" rel="stylesheet" />
</head>

<body>
  <nav class="navbar navbar-color-on-scroll navbar-transparent fixed-top navbar-expand-lg" color-on-scroll="100">
    <div class="container">
      <div class="navbar-translate">
          
      </div>
    </div>
  </nav>
  <div class="page-header header-filter" data-parallax="true" style="background-image: url('assets/img/bg3.jpg')">
    <div class="container">
      <div class="row">
        <div class="col-md-8 ml-auto mr-auto">
          <div class="brand text-center">
           
            <h1 class="title" >Lab Inspector<h1>

            
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="main main-raised">
    <div class="container">
      <div class="section text-center">
       <h3><b>Total Record of Current Exam<b><h3>
            <br>




        <div class="row">

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

          <div class="col-md-6">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">  <%=resultSet.getString("rno") %>  </h4>
                
              </div>
              <div class="card-body">
                 <h3 class="title"><%=resultSet.getString("msg") %><h3>
                 <img style="height: 250px;width: 100%;" src="abc<%= cnt %>.jpeg" />  
              </div>
            </div>
         </div>

         <%

          }
          connection.close();
          } catch (Exception e) {
          e.printStackTrace();
          }
          %>

        </div>










      </div>
    </div>
  </div>
  <footer class="footer footer-default">
    <div class="container">
      <nav class="float-left">
        <ul>
          <li>
            <a href="https://www.creative-tim.com/">
              Total Survelliance
            </a>
          </li>
        </ul>
      </nav>
     
    </div>
  </footer>
</body>

</html>