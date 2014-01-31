package cs4111;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.pool.OracleDataSource;
/**
* Servlet implementation class OracleServlet
*/
public class Make_order extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string = 
"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;

/**
 * @see HttpServlet#HttpServlet()
 */
public Make_order() {
    super();
//TODO Auto-generated constructor stub
}
/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
PrintWriter pw = new PrintWriter(response.getOutputStream());
try { 
if (conn == null) {
//Create a OracleDataSource instance and set URL
 OracleDataSource ods = new OracleDataSource();
 ods.setURL(connect_string); 
 conn = ods.getConnection();
 }

Statement stmt = conn.createStatement();   
String cid = request.getParameter("cid");
int user_id=Integer.parseInt(cid);
request.getSession().setAttribute("str1", cid);

Date date=new Date();
String time=date.toString();
time=time.substring(4,20);
response.setContentType("text/html");
pw.println("<html>");	

pw.println("<H2>"+time+"<H2>");

pw.println("<H2>"+"Please fill in the form to make a reservation"+"</H2>" );
pw.println("<br>");
pw.println("<br>");
//
pw.println("<p>");
pw.println("<img src="+'"'+"E:/javaweb/CS4111/WebContent/d.jpg"+'"'+" />");
pw.println("</p>");

pw.println("<form action=" + '"' + "Reservation" + '"' + "method=" + '"' + "get" +'"' + ">" );
pw.println("<H5>"+"Estimated Arrive Time(eg.2012/05/20 15:00:00)"+"</H5>");

pw.println("<input type=" + '"'+ "text" + '"' + "name=" + '"' + "arrive_time" + '"'+">");
pw.println("<br>");
pw.println("<br>");
pw.println("<H5>"+"Number of people(please input a number(no more than ten) eg.4)"+"</H5>");

pw.println("<input type=" + '"'+ "text" + '"' + "name=" + '"' + "num_people"  + '"'+">");
pw.println("<br>");
pw.println("<br>");
pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "order_time" + '"' + "value=" + '"'+time+'"' + ">");
pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + user_id + ">");
try {
	conn.close();
	conn=null;
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
pw.println("<button type=" + '"' + "submit" + '"' + ">Summit</button>");
pw.println("</form>");
pw.println("</html>");	

pw.println("<form action=" + '"' + "System_homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
pw.println("<button type=" + '"' + "submit" + '"' + ">Homepage</button>");
pw.println("</form>");


} catch (SQLException e) {
 pw.println(e.getMessage());
}

pw.close();
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
//TODO Auto-generated method stub
}
}