<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.07.2020
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <jsp:include page="/template/nav.jsp"/>
</head>
<body>
<h2>Change Librarian Info</h2>
<form action="librarian" method="post">
    <div class="form-group">
        <input type="hidden" name="librarianId" value="${librarian.id}">
        <label>Enter Name: </label>
        <input class="form-control" type="text" name="firstName" value="${librarian.firstName}">
        <label>Enter Surname: </label>
        <input class="form-control" type="text" name="lastName" value="${librarian.lastName}">
        <label>Enter Phone: </label>
        <input class="form-control" type="text" name="phone" value="${librarian.phone}">
        <label>Enter Email: </label>
        <input class="form-control" type="email" name="email" value="${librarian.email}">
        <label>Enter Experience: </label>
        <input class="form-control" type="text" name="experience" value="${librarian.experience}">
        <label>Enter Password: </label>
        <input class="form-control" type="text" name="password" value="${librarian.password}">
    </div>
    <button type="submit" name="state" value="librarianUpdate">Update</button>
</form>
</body>
</html>
