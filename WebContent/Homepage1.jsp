<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Columbia Campus Activity (CCA)</title>
</head>
<body>
<H2>Welcome to Campus Activity (CCA)</H2>
<br>
If you have an account, please logon here.
<br><br>
</body>

<form action="Logon" method="get"> 

Logon ID


<br>
  <input type="text" name="usid">
  <br>
Password



<br>
  <input type="text" name="pswd">
<br>
Log on as



<select name="user_type"> 
  <option value="Hosts">Hosts</option>
  <option value="Participants">Participants</option>
  <option value="Admin">Admin</option>
</select>

<button type="submit">Logon</button> 
</form> 



<br>
<br>
<body>
If you don't have an account, you can register here.
</body>
<br><br>
<form action="Register" method="get"> 
<select name="register_user_type">
  <option value="Hosts">Hosts</option>
  <option value="Participants">Participants</option>
</select>

<button type="submit">Register</button>
</form>




</html>

