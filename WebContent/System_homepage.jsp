<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String c_id= (String)session.getAttribute("str1");
//out.print(c_id);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dennial's Restaurant</title>
</head>
<body>
<H2>Welcome to Dennial's Restaurant</H2>
<br>
<form action="Transfer" method="get"> 
<select name="user_type"> 
  <option value="logout">logout</option>
  <option value="status">order state</option>
  <option value="comments">comments</option>
</select>
<%
out.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
out.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "add_comm" + '"' + "value=" + "no" + ">");
%>

<button type="submit">Enter</button>
</form>
 

<br>
Our online system have two services, please make your choice! 
<br>

<form action="Menu" method="get">
<%
out.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
out.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "add_comm" + '"' + "value=" + "no" + ">");
%>
  <button type="submit">Delivery_dish</button>

</form>
<p>
<img src="E:\javaweb\CS4111\WebContent\c.gif" width="160" height="160"/>
</p>


<form action="Make_order" method="get">
<%
out.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "cid" + '"' + "value=" + c_id + ">");
out.print("<input type=" + '"'+ "hidden" + '"' + "name=" + '"' + "add_comm" + '"' + "value=" + "no" + ">");
%>
  <button type="submit">Make_order</button>
</form>

<p>
<img src="E:\javaweb\CS4111\WebContent\b.jpg" width="160" height="160"/>
</p>
<br>

</body>


</html>

