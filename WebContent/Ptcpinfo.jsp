<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
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
		ods.setURL("jdbc:oracle:thin:sc3331/cshw929@//w4111c.cs.columbia.edu:1521/ADB");
		conn = ods.getConnection();


		String eventsid= (String)session.getAttribute("str5");
		String hostsid= (String)session.getAttribute("str6");

		Statement stmt = conn.createStatement();
		
		//rset = stmt.executeQuery("select P.name, P.pid, P.email from RegistersFor F, Runs R, Participants P  where R.eid = F.eid and P.pid=F.pid and R.hid = '"
				//+ hostsid +"' and R.eid=" + eventsid);
		
		
		rset = stmt.executeQuery("select P.pid, P.name, P.email from Participants P, RegistersFor F where F.pid=P.pid and F.eid=" + eventsid);
				
				//("select P.pid, P.name, P.email from Participants P, RegistersFor F where F.pid=P.pid and F.eid= " + "'"
				//+ ppid + "'");


	} catch (SQLException e) {
		error_msg = e.getMessage();
		if( conn != null ) {
			conn.close();
		}
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Participants List</title>
</head>
<body>

<TABLE border=1 width=500>
<tr bgcolor=F6CEF5>
<th>Participant ID</th><th>Name</th><th>Email</th>
</tr>



<%
	if(rset != null) {
		while(rset.next()) {
			out.print("<tr bgcolor=ffffff>");
			out.print("<td>" + rset.getString("pid") + "</td><td>" +	rset.getString("name") + "</td>" + "<td>" + rset.getString("email") + "</td>");
			out.print("</tr>");
		}
	} 
	else {
		out.print(error_msg);
	}
	if( conn != null ) {
		conn.close();
	}	
%>

</TABLE>
</body>
</html>

