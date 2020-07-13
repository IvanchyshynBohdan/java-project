<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.07.2020
  Time: 20:09
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
        <td>Book Name</td>
        <td>Member Name</td>
        <td>Borrow date</td>
        <td>Return Date</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="issue" items="${issues}">
        <tr>
            <td><c:out value="${issue.id}"/></td>
            <td><c:out value="${issue.books.title}"/></td>
            <td><c:out value="${issue.member.lastName}"/></td>
            <td><c:out value="${issue.takenDate}"/></td>
            <td><c:out value="${issue.broughtDate}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
