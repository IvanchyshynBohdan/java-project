<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.07.2020
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <jsp:include page="/template/nav.jsp"/>
</head>
<body style="text-align: center">
<h2>Member Profile</h2>
<table class="table table-bordered">
    <thead>
    <tr>
        <td>Name:</td>
        <td>${member.firstName}</td>
    </tr>
    <tr>
        <td>Surname:</td>
        <td>${member.lastName}</td>
    </tr>
    <tr>
        <td>Phone number:</td>
        <td>${member.phone}</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>${member.email}</td>
    </tr>
    <tr>
        <td>City:</td>
        <td>${member.city}</td>
    </tr>
    <tr>
        <td>Password:</td>
        <td>${member.password}</td>
    </tr>
    </thead>
</table>
</body>
</html>