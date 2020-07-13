<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07.07.2020
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Info</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <jsp:include page="/template/nav.jsp"/>
</head>
<body>
    <h2>Change Member Profile</h2>
    <form action="member" method="post">
        <div class="form-group">
        <label>Enter Name: </label>
        <input type="hidden" name="memberId" value="${member.id}">
        <input class="form-control" type="text" name="firstName" value="${member.firstName}">
        <label>Enter Surname: </label>
        <input class="form-control" type="text" name="lastName" value="${member.lastName}">
        <label>Enter Phone: </label>
        <input class="form-control" type="text" name="phone" value="${member.phone}">
        <label>Enter Email: </label>
        <input class="form-control" type="email" name="email" value="${member.email}">
        <label>Enter Password: </label>
        <input class="form-control" type="text" name="password" value="${member.password}">
        <label>Enter City: </label>
        <input class="form-control" type="text" name="city" value="${member.city}">
        </div>
        <button type="submit" name="state" value="memberUpdate">Update</button>
    </form>
</body>
</html>
