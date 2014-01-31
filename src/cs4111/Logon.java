package cs4111;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;


public class Logon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connect_string = 
			"jdbc:oracle:thin:kr2496/BnFwhVzQ@//w4111g.cs.columbia.edu:1521/ADB";
	private Connection conn;


	public Logon() {
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
			String cid = request.getParameter("cid1");
			String cname = request.getParameter("name1");
			response.setContentType("text/html");
			pw.println("<html>");
			request.getSession().setAttribute("str1", cid);

			
			ResultSet rset = stmt.executeQuery("select cid,name  from customer");
			int count=0;
			
		
			
			
			
			while(rset.next())
			{
				
				if (cid.equals(rset.getString("cid")) && cname.equals(rset.getString("name")))
				{
					count=count+1;
					
					pw.println("<head><title>Welcome" + " " + cid + " " + cname + "</title></head>");
					pw.println("<H1>" + "Welcome " + rset.getString("name") + " you have successfully login!"+"!<BR></H1>" );
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
					break;
				}
			}
			if(count==0)
			{
				pw.println("<head><title>Error!</title></head>");
				pw.println("Invalid name or cid. Please try again.");
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
		
			
			
		}catch (SQLException e) {
			pw.println(e.getMessage());
		}
	
		pw.close();
	}
}
