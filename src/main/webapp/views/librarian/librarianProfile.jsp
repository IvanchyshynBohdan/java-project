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
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <jsp:include page="/template/nav.jsp"/>
</head>
<body style="text-align: center">
<h2>Librarian Profile</h2>
<table class="table table-bordered">
    <thead>
    <tr>
        <td>Id: </td>
        <td>${librarian.id}</td>
    </tr>
    <tr>
        <td>Name: </td>
        <td>${librarian.firstName}</td>
    </tr>
    <tr>
        <td>Surname: </td>
        <td>${librarian.lastName}</td>
    </tr>
    <tr>
        <td>Phone number: </td>
        <td>${librarian.phone}</td>
    </tr>
    <tr>
        <td>Email: </td>
        <td>${librarian.email}</td>
    </tr>
    <tr>
        <td>Experience: </td>
        <td>${librarian.experience} years</td>
    </tr>
    <tr>
        <td>Password: </td>
        <td>${librarian.password}</td>
    </tr>
    </thead>
</table>
</body>
</html>
