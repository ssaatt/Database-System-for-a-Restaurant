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
public class Menu extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string = 
"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;

/**
 * @see HttpServlet#HttpServlet()
 */
public Menu() {
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

response.setContentType("text/html");
pw.println("<html>");	

pw.println("<H2>"+"The Menu Information are as follows:"+"</H2>");

pw.println("<form action=" + '"' + "Delivery" + '"' + "method=" + '"' + "get" +'"' + ">" );
// Appetizer information
pw.println("<H2>"+"Appetizer:"+"</H2>");
ResultSet rset = stmt.executeQuery("select M.dishID,M.dname,M.price,A.discount from Menu M,Appetizer A where M.dishID=A.dishID");
pw.println("<table border=1 width=500");
pw.println("<tr bgcolor=A9A9F5><th>Check box</th> <th>Dish ID</th> <th>Dish Name</th> <th>Dish Price</th> <th>Discount</th> ");



while(rset.next())
{
	int did1=rset.getInt("dishID");
	String dname1=rset.getString("dname");
	Float price1=rset.getFloat("price");
	String discount1=rset.getString("discount");
	pw.println("<tr bgcolor=FFFFFF>");
	
	
	pw.print("<td>"+"<input type=" + '"'+ "checkbox" + '"' + "name=" + '"' + "did" + '"' + "value=" + did1 + ">"+"</td>");
	pw.print("<td>" + did1 + "</td>"+"<td>" + dname1 + "</td>" + "<td>" + price1 + "</td>" + "<td>" + discount1+ "</td>");
	pw.println("</tr>");
}
pw.println("</table>");
pw.println("<br>");
pw.println("<br>");

// Soup information
pw.println("<H2>"+"Soup:"+"</H2>");
ResultSet rset1 = stmt.executeQuery("select M.dishID,M.dname,M.price,S.ingredient from Menu M,Soup S where M.dishID=S.dishID");
pw.println("<table border=1 width=500");
pw.println("<tr bgcolor=A9A9F5><th>Check box</th> <th>Dish ID</th> <th>Dish Name</th> <th>Dish Price</th> <th>Ingredient</th> ");
while(rset1.next())
{
	int did2=rset1.getInt("dishID");
	String dname2=rset1.getString("dname");
	Float price2=rset1.getFloat("price");
	String ingredient=rset1.getString("ingredient");
	pw.println("<tr bgcolor=FFFFFF>");
	
	
	pw.print("<td>"+"<input type=" + '"'+ "checkbox" + '"' + "name=" + '"' + "did" + '"' + "value=" + did2 + ">"+"</td>");
	pw.print("<td>" + did2 + "</td>"+"<td>" + dname2 + "</td>" + "<td>" + price2 + "</td>" + "<td>" + ingredient+ "</td>");
	pw.println("</tr>");
}
pw.println("</table>");
pw.println("<br>");
pw.println("<br>");

// Fruit_salad information
pw.println("<H2>"+"Fruit_salad:"+"</H2>");
ResultSet rset2 = stmt.executeQuery("select M.dishID,M.dname,M.price,F.type_of_fruit from Menu M,Fruit_salad F where M.dishID=F.dishID");
pw.println("<table border=1 width=500");
pw.println("<tr bgcolor=A9A9F5><th>Check box</th> <th>Dish ID</th> <th>Dish Name</th> <th>Dish Price</th> <th>Ingredient</th> ");
while(rset2.next())
{
	int did3=rset2.getInt("dishID");
	String dname3=rset2.getString("dname");
	Float price3=rset2.getFloat("price");
	String type_of_fruit=rset2.getString("type_of_fruit");
	pw.println("<tr bgcolor=FFFFFF>");
	
	
	pw.print("<td>"+"<input type=" + '"'+ "checkbox" + '"' + "name=" + '"' + "did" + '"' + "value=" + did3 + ">"+"</td>");
	pw.print("<td>" + did3 + "</td>"+"<td>" + dname3 + "</td>" + "<td>" + price3 + "</td>" + "<td>" + type_of_fruit+ "</td>");
	pw.println("</tr>");
}
pw.println("</table>");
pw.println("<br>");
pw.println("<br>");

//Lunch_special
//String s=new String;
pw.println("<H2>"+"Lunch_special:"+"</H2>");
ResultSet rset3 = stmt.executeQuery("select M.dishID,M.dname,M.price,L.offering_time from Menu M,lunch_special L where M.dishID=L.dishID");


pw.println("<table border=1 width=500");
pw.println("<tr bgcolor=A9A9F5><th>Check box</th> <th>Dish ID</th> <th>Dish Name</th> <th>Dish Price</th> <th>Offering_time</th> ");
while(rset3.next())
{
	int did4=rset3.getInt("dishID");
	String dname4=rset3.getString("dname");
	Float price4=rset3.getFloat("price");
	String offering_time=rset3.getString("offering_time");
	pw.println("<tr bgcolor=FFFFFF>");
	pw.print("<td>"+"<input type=" + '"'+ "checkbox" + '"' + "name=" + '"' + "did" + '"' + "value=" + did4 + ">"+"</td>");
	pw.print("<td>" + did4 + "</td>"+"<td>" + dname4 + "</td>" + "<td>" + price4 + "</td>" + "<td>" + offering_time+ "</td>");
	pw.println("</tr>");
}

try {
	conn.close();
	conn=null;
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

pw.println("</table>");
pw.println("<br>");
pw.println("<br>");
pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + cid + ">");
pw.println("<button type=" + '"' + "submit" + '"' + ">Confirm</button>");
pw.println("</form>");

pw.println("<form action=" + '"' + "System_homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + cid + ">");
pw.println("<button type=" + '"' + "submit" + '"' + ">Homepage</button>");
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