package cs4111;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.pool.OracleDataSource;
/**
* Servlet implementation class OracleServlet
*/
public class Register extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string = 
"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;

/**
 * @see HttpServlet#HttpServlet()
 */
public Register() {
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
String user_name = request.getParameter("name");
String user_tel = request.getParameter("telephone");
String user_add = request.getParameter("address");
String user_email=request.getParameter("email");
if(user_name.equals("")||user_tel.equals("")||user_add.equals("")||user_email.equals(""))
{
	pw.println("<head><title>Error!</title></head>");
	pw.println("<H1>Invalid name or information. Please try again.");
	pw.println("<form action=" + '"' + "Homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
	try {
		conn.close();
		conn=null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pw.println("<button type=" + '"' + "submit" + '"' + ">Try Again</button>");
	pw.println("</form>");
	
}
else if((user_tel.length()!=10))
{
	pw.println("<head><title>Error!</title></head>");
	pw.println("<H1>Invalid telephone number (must be 10 numbers). Please try again.");
	pw.println("<form action=" + '"' + "Homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
	try {
		conn.close();
		conn=null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pw.println("<button type=" + '"' + "submit" + '"' + ">Try Again</button>");
	pw.println("</form>");
}
else
{
	ResultSet rset = stmt.executeQuery("select max(cid) as max_cid from customer");
	rset.next();
	int mcid=rset.getInt("max_cid");
	mcid++;
	String cid=Integer.toString(mcid);
			
	stmt.executeQuery("insert into customer(cid, name,telephone,email,address) values(" + "'" + mcid + "','" + user_name + "','"+user_tel+"','"+user_email+"','"+user_add+"')");	

	
	response.setContentType("text/html");
	pw.println("<html>");
	request.getSession().setAttribute("str1", cid);
	pw.println("<head><title>Welcome</title></head>");
	pw.println("<H1>Dennial's Restaurant<BR></H1>");
	pw.println("<body><BR>");
	pw.println("<H2>Welcome customer ID: " + " " + mcid + " " + user_name + "<br><H2>");
	pw.println("<br>");
	pw.println("<form action=" + '"' + "System_homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
	try {
		conn.close();
		conn=null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pw.println("<button type=" + '"' + "submit" + '"' + ">Enter System</button>");
	pw.println("</form>");

	 pw.println("</body></html>");
	
}

} catch (SQLException e) {
 pw.println(e.getMessage());
}

pw.close();



/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
response)
*/


}

protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
//TODO Auto-generated method stub
}

}