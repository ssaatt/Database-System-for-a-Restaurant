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
public class Delete_delivery extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string = 
"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;

/**
 * @see HttpServlet#HttpServlet()
 */
public Delete_delivery() {
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
String user_id = request.getParameter("cid");
int cid=Integer.parseInt(user_id);
String user_did = request.getParameter("did");
int did=Integer.parseInt(user_did);

//ResultSet rcom = stmt.executeQuery("select dishID from generate where deliveryID="+did);
//rcom.next();
//int dishID=rcom.getInt("dishID");

ResultSet rel = stmt.executeQuery("delete from delivery_to where deliveryID="+did);
ResultSet res = stmt.executeQuery("delete from generate where deliveryID="+did);
//ResultSet res1 = stmt.executeQuery("delete from choose where dishID="+dishID+" and cid="+cid);


response.setContentType("text/html");
pw.println("<html>");
pw.println("<H4>"+"Delete delivery order sucessufully!"+"</H4>");
pw.println("<form action=" + '"' + "Transfer" + '"' + "method=" + '"' + "get" +'"' + ">" );
pw.println("<br>");
pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "user_type" + '"' + "value=" + "status" + ">");
pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + cid + ">");
try {
	conn.close();
	conn=null;
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
pw.println("<button type=" + '"' + "submit" + '"' + ">Return Back</button>");
pw.println("</form>");

pw.println("</html>");


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
