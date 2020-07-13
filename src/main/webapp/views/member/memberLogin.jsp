<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.07.2020
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<h2 style="text-align: center">Log In</h2>
<form action="login" method="post">
    <div class="form-group">
        <label>Email</label>
        <input class="form-control" type="email" name="email">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input class="form-control" type="password" name="password">
    </div>
    <button class="form-control" name="state" type="submit" value="memberLogin">Login</button>
</form>
</body>
</html>
