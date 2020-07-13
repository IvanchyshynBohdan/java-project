<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07.07.2020
  Time: 0:11
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
        <td>Title</td>
        <td>Author</td>
        <td>Category</td>
        <td>Publisher</td>
        <td>Price</td>
        <td>Delete</td>
        <td>Update</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td><c:out value="${book.id}"/></td>
            <td><c:out value="${book.title}"/></td>
            <td><c:out value="${book.author}"/></td>
            <td><c:out value="${book.category.name}"/></td>
            <td><c:out value="${book.publisher.name}"/></td>
            <td><c:out value="${book.price}"/></td>
            <td><a href="book?state=deleteBook&bookId=${book.id}">Delete</a></td>
            <td><a href="book?state=updateBook&bookId=${book.id}">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
