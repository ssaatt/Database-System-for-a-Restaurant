<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dennial's Restaurant</title>
</head>
<body>
<H2>Welcome to Dennial's Restaurant</H2>
<br>
</body>
<p>
<img src="E:\javaweb\CS4111\WebContent\a.jpg" />
</p>
<body>
<br>
If you have an account, please login here.
<br>
</body>
<br>
<form action="Logon" method="get"> 
Customer ID
<br>
  <input type="text" name="cid1">
<br>
Customer name
<br>
<input type="text" name="name1">
<br>
<button type="submit">Logon</button> 
</form> 
<br>
<br>
<body>
If you don't have an account, you can register here.
</body>
<br><br>
<form action="Register" method="get"> 
<br>
Customer name
<br>
<input type="text" name="name">
<br>
telephone( must be 10 numbers)
<br>
<input type="text" name="telephone">
<br>
email
<br>
<input type="text" name="email">
<br>
Address
<br>
<input type="text" name="address">
<br>
<button type="submit">Register</button>
</form>

</html>

