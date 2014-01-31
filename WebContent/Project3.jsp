<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- This import is necessary for JDBC -->
<%@ page import="java.sql.*"%>
<%@ page import="oracle.jdbc.pool.OracleDataSource"%>
<!-- Database lookup -->
<%
Connection conn = null;
ResultSet rset = null;
String error_msg = "";
try {
OracleDataSource ods = new OracleDataSource();
ods.setURL("jdbc:oracle:thin:scott/tiger@//w4111b.cs.columbia.edu:1521/ADB"); 
conn = ods.getConnection();
Statement stmt = conn.createStatement();
rset = stmt.executeQuery("select empno, ename, sal from emp");
} catch (SQLException e) {
error_msg = e.getMessage();
if( conn != null ) {
conn.close();
}
}
%>
<html>
<h2>Employee Table</h2><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Table JSP Sample</title>
</head>
<body>
    
    <TABLE>
    <tr>
      <td>EMPNO</td><td>ENAME</td><td>SAL</td>
    </tr>
    <tr>
      <td><b>----------</b></td><td><b>----------</b></td><td><b>----------</b></td>
    </tr>
<%
     if(rset != null) {
       while(rset.next()) {
         out.print("<tr>");
         out.print("<td>" + rset.getInt("empno") + "</td><td>" + 
rset.getString("ename") + "</td>" +
                  "<td>" + rset.getInt("sal") + "</td>");
         out.print("</tr>");
       }
     } else {
     out.print(error_msg);
     }
if( conn != null ) {
conn.close();
}
    %>
</TABLE>
</body>
</html>




