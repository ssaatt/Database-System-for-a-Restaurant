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
public class Reservation extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String connect_string = 
"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
private Connection conn;

/**
 * @see HttpServlet#HttpServlet()
 */
public Reservation() {
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
String c_id = request.getParameter("cid");
String ordertime = request.getParameter("order_time");
String arrivetime = request.getParameter("arrive_time");
String num_people=request.getParameter("num_people");
int error=0;
//pw.println(error);
String[] as = arrivetime.split(" ");
//pw.println(as.length);
if(as.length==2)
{
	//pw.println(error);
	String testdata=as[0].trim();
	String testtime=as[1].trim();

	java.text.SimpleDateFormat format=new java.text.SimpleDateFormat
	("yyyy/MM/dd");
	try{
	format.parse(testdata);
	}catch(Exception e){
	error=1;
	}



	if(error==0)
	{
		//pw.println(error);
		String[] date=testdata.split("/");
	//	pw.println(date[0]);
	//	pw.println(date[1]);
	//	pw.println(date[2]);
		
		if((Integer.parseInt(date[0])>2011&&Integer.parseInt(date[0])<2014)&&(Integer.parseInt(date[1])>0&&Integer.parseInt(date[1])<13)&&(Integer.parseInt(date[2])>0&&Integer.parseInt(date[2])<32))
		{
			String[] time=testtime.split(":");
			if(Integer.parseInt(time[0])<24&&Integer.parseInt(time[1])<60&&Integer.parseInt(time[2])<60)
			{
				
			}
			else
			{
				error=1;
			}
		}
		else
		{
			error=1;
		}
		
	}

}
else
{
	error=1;
	pw.println(error);
}



if(arrivetime.equals(""))
{
	response.setContentType("text/html");
	pw.println("<html>");
	pw.println("<H4>"+"Arrivetime can not be void!"+"</H4>");
	
	pw.println("<form action=" + '"' + "Make_order" + '"' + "method=" + '"' + "get" +'"' + ">" );
	pw.println("<br>");
	pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
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
else if(error==1)
{
	response.setContentType("text/html");
	pw.println("<html>");
	pw.println("<H4>"+"The arrive time format is not correct!"+"</H4>");
	
	pw.println("<form action=" + '"' + "Make_order" + '"' + "method=" + '"' + "get" +'"' + ">" );
	pw.println("<br>");
	pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
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
else if(num_people.length()==0)
{
	response.setContentType("text/html");
	pw.println("<html>");
	pw.println("<H4>"+"Number of people cam not be void!"+"</H4>");
	pw.println("<form action=" + '"' + "Make_order" + '"' + "method=" + '"' + "get" +'"' + ">" );
	pw.println("<br>");
	pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
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
else if(Integer.parseInt(num_people)>10)
{
	response.setContentType("text/html");
	pw.println("<html>");
	pw.println("<H4>"+"Number of people can not exceed 10!"+"</H4>");
	pw.println("<form action=" + '"' + "Make_order" + '"' + "method=" + '"' + "get" +'"' + ">" );
	pw.println("<br>");
	pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
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
	int cid=Integer.parseInt(c_id);
	request.getSession().setAttribute("str1", c_id);

	ResultSet req = stmt.executeQuery("select max(oid) as max_oid from reservation_make");

	req.next();
	int oid=req.getInt("max_oid");
	oid++;
	stmt.executeQuery("insert into reservation_make(oid, cid,ordertime,arrivetime,no_people) values(" + "'" + oid + "','" + cid + "','"+ ordertime +"','" + arrivetime + "','" + num_people + "')");
	//select a table that does not reach the capacity and with max table number
	int tid=oid%12;
	stmt.executeQuery("insert into is_at(cid,tid,oid) values(" + "'" + cid + "','" + tid + "','"+ oid +"')");


	response.setContentType("text/html");
	pw.println("<html>");	
	pw.println("<H4>"+"Reservation have been successfully added!"+"</H4>");
	pw.println("<form action=" + '"' + "System_homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
	pw.println("<br>");
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