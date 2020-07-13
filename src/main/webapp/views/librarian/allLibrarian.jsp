<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.07.2020
  Time: 13:06
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
<table class="table table-bordered">
    <thead>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Phone Number</td>
        <td>Email</td>
        <td>Password</td>
        <td>Experience</td>
        <td>Delete</td>
        <td>Update</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="librarian" items="${librarians}">
        <tr>
            <td><c:out value="${librarian.id}"/></td>
            <td><c:out value="${librarian.firstName}"/></td>
            <td><c:out value="${librarian.lastName}"/></td>
            <td><c:out value="${librarian.phone}"/></td>
            <td><c:out value="${librarian.email}"/></td>
            <td><c:out value="${librarian.password}"/></td>
            <td><c:out value="${librarian.experience}"/></td>
            <td><a href="librarian?state=deleteLibrarian&librarianId=${librarian.id}">Delete</a></td>
            <td><a href="librarian?state=updateLibrarian&librarianId=${librarian.id}">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
