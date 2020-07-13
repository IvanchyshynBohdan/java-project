<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.07.2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<h2>Log In</h2>
<form action="login" method="post" style="text-align: center">
    <div class="form-group">
    <label>Email</label>
    <input class="form-control" type="email" name="email">
    <label>Password</label>
    <input class="form-control" type="password" name="password">
    <button name="state" type="submit" value="librarianLogin">Login</button>
    </div>
</form>
</body>
</html>
