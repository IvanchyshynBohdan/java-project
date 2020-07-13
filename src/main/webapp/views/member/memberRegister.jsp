<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.07.2020
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<form action="member" method="post">
    <div class="form-group">
    <h2>Create Account</h2>
    <label>Enter Name</label>
    <input class="form-control" type="text" name="name">
    <label>Enter Surname</label>
    <input class="form-control" type="text" name="surname">
    <label>Enter Email</label>
    <input class="form-control" type="email" name="email">
    <label>Enter Phone Number</label>
    <input class="form-control" type="text" name="phone">
    <label>Enter City</label>
    <input class="form-control" type="text" name="city">
    <label>Enter Password</label>
    <input class="form-control" type="password" name="password">
    </div>
    <button type="submit" name="state" value="memberRegister">Create</button>
</form>
</body>
</html>
