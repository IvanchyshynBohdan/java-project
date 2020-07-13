<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.07.2020
  Time: 17:29
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
        <td>Phone</td>
        <td>Email</td>
        <td>Password</td>
        <td>City</td>
        <td>Delete</td>
        <td>Update</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="member" items="${members}">
        <tr>
            <td><c:out value="${member.id}"/></td>
            <td><c:out value="${member.firstName}"/></td>
            <td><c:out value="${member.lastName}"/></td>
            <td><c:out value="${member.phone}"/></td>
            <td><c:out value="${member.email}"/></td>
            <td><c:out value="${member.password}"/></td>
            <td><c:out value="${member.city}"/></td>
            <td><a href="member?state=deleteMember&memberId=${member.id}">Delete</a></td>
            <td><a href="member?state=updateMember&memberId=${member.id}">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
