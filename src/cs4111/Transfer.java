package cs4111;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;


public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string = 
			"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;


	public Transfer() {
			super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		try {
			if (conn == null) {
// Create a OracleDataSource instance and set URL
				OracleDataSource ods = new OracleDataSource();
				ods.setURL(connect_string);
				conn = ods.getConnection();
			}
			Statement stmt = conn.createStatement();
			
			
			
			
			String user_tp = request.getParameter("user_type");
			String comm_tp = request.getParameter("add_comm");
			String cid = request.getParameter("cid");
			int c_id=Integer.parseInt(cid);
			
			response.setContentType("text/html");
			pw.println("<html>");	
			request.getSession().setAttribute("str1", cid);
			//pw.println("<H2>"+cid+"</H2>");
			

			
			if(user_tp.equals("logout"))
			{
				pw.println("<H2>"+"You have successfully logout!"+"</H2>");
				pw.println("<br>");
				pw.println("<form action=" + '"' + "Homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
				try {
					conn.close();
					conn=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pw.println("<button type=" + '"' + "submit" + '"' + ">Continue</button>");
				pw.println("</form>");
				
			}
			else if(user_tp.equals("comments"))
			{
				
				if(comm_tp.equals("add"))
				{
					
					String comments =request.getParameter("comment");
					if(comments.equals(""))
					{
						pw.println("<H4>"+"Comments can not be void!"+"</H4>");
						pw.println("<br>");		
					}
					else 
					{
						ResultSet rl = stmt.executeQuery("select max(commentID) as max_c from write_comments");
						rl.next();
						int commID=rl.getInt("max_c");
						commID++;
						stmt.executeQuery("insert into write_comments(commentID, cid,text) values(" + "'" + commID + "','" + cid + "','"+ comments +"')");	
						
						pw.println("<H4>"+"Comments have been successfully added!"+"</H4>");
						
					}
					
					
					
				}
				ResultSet rel=stmt.executeQuery("select C.name,W.commentID,W.text from write_comments W,customer C where C.cid=W.cid");
				
				pw.println("<table border=1 width=500");
				pw.println("<tr bgcolor=A9A9F5><th>Comments ID</th> <th>Customer name</th> <th>Comments</th>");
				while(rel.next())
				{
					String cus_name=rel.getString("name");
					int com_id=rel.getInt("commentID");
					String comments=rel.getString("text");
					pw.println("<tr bgcolor=FFFFFF>");
					pw.print("<td>" + com_id + "</td>"+"<td>" + cus_name + "</td>" + "<td>" + comments + "</td>");
					pw.println("</tr>");
				}
				pw.println("</table>");
				pw.println("<br>");
					
				pw.println("<H4>"+"please write your comments here:"+"</H4>");
				
				pw.println("<form action=" + '"' + "Transfer" + '"' + "method=" + '"' + "get" +'"' + ">" );
				pw.println("<textarea name="+'"'+"comment"+'"'+"rows="+'"'+"5"+'"'+"cols="+'"'+"60"+'"'+">");
				pw.println("</textarea>");
				pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "user_type" + '"' + "value=" + "comments" + ">");
		        pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
		        pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "add_comm" + '"' + "value=" + "add" + ">");
				pw.println("<br>");
				try {
					conn.close();
					conn=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pw.println("<button type=" + '"' + "submit" + '"' + ">Summit</button>");
				pw.println("</form>");
			    
				pw.println("<form action=" + '"' + "System_homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
				
				pw.println("<button type=" + '"' + "submit" + '"' + ">Homepage</button>");
				pw.println("</form>");
				
				
			//	pw.println("<form action="+'"'+"Homepage.jsp"+'"'+" method="+'"'+"POST"+"'"+">");
		//		pw.println("<P>");
				//pw.println("<input type="+"submit"+">"+"<input type="+"reset"+">");
			//	pw.println("</form>");
				
			}
			else if(user_tp.equals("status"))
			{
				pw.println("<H2>"+"The Order Information are as follows:"+"</H2>");
				ResultSet rset = stmt.executeQuery("select * from reservation_make R,is_at I where R.oid=I.oid and  R.cid="+c_id);
				pw.println("<table border=1 width=500");
				pw.println("<tr bgcolor=A9A9F5><th>Order ID</th> <th>Customer ID</th> <th>Order Time</th> <th>Arrive Time</th> <th>People Served</th>  <th>Table Number</th> <th>Action</th>");
				while(rset.next())
				{
					int oid=rset.getInt("oid");
					int cid_num=rset.getInt("cid");
					String order_time=rset.getString("ordertime");
					String arrive_time=rset.getString("arrivetime");
					int num_people=rset.getInt("no_people");
					int table_num=rset.getInt("tid");
					pw.println("<tr bgcolor=FFFFFF>");
					pw.print("<td>" + oid + "</td>"+"<td>" + cid_num + "</td>" + "<td>" + order_time + "</td>" + "<td>" + arrive_time+ "</td>" + "<td>"+ num_people +"</td>"+"<td>"+ table_num +"</td>");
					
					pw.println("<form action=" + '"' + "Delete_order" + '"' + "method=" + '"' + "get" +'"' + ">" );
			
					pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "oid" + '"' + "value=" + oid + ">");
					pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + cid + ">");
					
					pw.print("<td>"+"<button type=" + '"'+ "submit" + '"' + ">Delete</button>"+"</td>");
					pw.println("</form>");
					
					pw.println("</tr>");
					//pw.println("<H2>"+cid_num+"</H2>");
					//pw.println("<H2>"+oid+"</H2>");
					//pw.println("<H2>"+order_time+"</H2>");
				}
				pw.println("</table>");
				pw.println("<br>");
				pw.println("<br>");
				
				ResultSet res = stmt.executeQuery("select D.deliveryID,C.name,D.dtime,M.price,M.dname,C.address from delivery_to D,customer C,generate G,menu M where M.dishID=G.dishID and G.deliveryID=D.deliveryID and D.cid=C.cid and C.cid="+c_id);
				pw.println("<H2>"+"The Dish Delivery Information are as follows:"+"</H2>");
				
				pw.println("<table border=1 width=500");
				pw.println("<tr bgcolor=A9A9F5><th>Delivery ID</th> <th>Customer name</th> <th>Delivery Time</th> <th>Dish Name</th> <th>Price</th> <th>Address</th> <th>Action</th>");
				while(res.next())
				{
					int did=res.getInt("deliveryID");
					String name=res.getString("name");
					String delivery_time=res.getString("dtime");
					String d_name=res.getString("dname");
					float price=res.getFloat("price");
					String addr=res.getString("address");
					pw.println("<tr bgcolor=FFFFFF>");
					pw.print("<td>" + did + "</td>"+"<td>" + name + "</td>" + "<td>" + delivery_time + "</td>" + "<td>" + d_name+ "</td>" + "<td>"+ price +"</td>"+ "<td>"+ addr +"</td>");
					
					
					pw.println("<form action=" + '"' + "Delete_delivery" + '"' + "method=" + '"' + "get" +'"' + ">" );
					pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + cid + ">");
					pw.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "did" + '"' + "value=" + did + ">");
					
					pw.print("<td>"+"<button type=" + '"'+ "submit" + '"' + ">Delete</button>"+"</td>");
					pw.println("</form>");
					
					
					pw.println("</tr>");
					//pw.println("<H2>"+cid_num+"</H2>");
					//pw.println("<H2>"+oid+"</H2>");
					//pw.println("<H2>"+order_time+"</H2>");
				}
				pw.println("</table>");
				pw.println("<br>");
				pw.println("<br>");
				pw.println("<form action=" + '"' + "System_homepage.jsp" + '"' + "method=" + '"' + "get" +'"' + ">" );
				try {
					conn.close();
					conn=null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				pw.println("<button type=" + '"' + "submit" + '"' + ">Return Back</button>");
				pw.println("</form>");
				
				
			}
			
			
			pw.println("</html>");	
			
		}catch (SQLException e) {
			pw.println(e.getMessage());
		}
		
		pw.close();
	}
}
