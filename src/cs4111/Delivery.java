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
public class Delivery extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string = 
"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;

/**
 * @see HttpServlet#HttpServlet()
 */
public Delivery() {
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
String[] did = request.getParameterValues("did");
//ResultSet rel = stmt.executeQuery("select M.dishID from Menu M");
if(did==null || (did!=null&&did.length==0))
{
	response.setContentType("text/html");
	pw.println("<html>");
	pw.println("<H4>"+"Selection can not be void!"+"</H4>");
	pw.println("<form action=" + '"' + "Menu" + '"' + "method=" + '"' + "get" +'"' + ">" );
	pw.println("<br>");
	pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + user_id + ">");
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
}
else
{
	request.getSession().setAttribute("str1", user_id);
	//automatic make up delivery id
	ResultSet rel = stmt.executeQuery("select max(deliveryID) as dmax from delivery_to");
	rel.next();
	int dmax=rel.getInt("dmax");
	dmax++;
	// get the date time
	Date date=new Date();
	String time=date.toString();
	time=time.substring(4,20);
	// get the total price
	float total_price=0;
	for(int j=0;j<did.length;j++)
	{
		ResultSet relset=stmt.executeQuery("select price from menu where dishID="+did[j]);
		relset.next();
		total_price=total_price+relset.getFloat("price");
		
	}
	stmt.executeQuery("insert into delivery_to(deliveryID,dtime,total_price,cid) values(" + "'" + dmax + "','" + time + "','" + total_price + "','" + cid + "')");

	for(int i=0;i<did.length;i++)
	{	
		//stmt.executeQuery("insert into choose(cid,dishID) values(" + "'" + cid + "','" + did[i] + "')");
		stmt.executeQuery("insert into generate(cid,dishID,deliveryID) values(" + "'" + cid + "','" + did[i] + "','" + dmax + "')");
	}

	response.setContentType("text/html");
	pw.println("<html>");
	pw.println("<H4>"+"Successfully added with delivery id: "+dmax+"</H4>");

	pw.println("<form action=" + '"' + "System_homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
	try {
		conn.close();
		conn=null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pw.println("<button type=" + '"' + "submit" + '"' + ">Continue</button>");
	pw.println("</form>");
	pw.println("</html>");

	
}




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
