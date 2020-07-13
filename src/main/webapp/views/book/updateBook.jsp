<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.07.2020
  Time: 16:34
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
<h2>Change Book Info</h2>
<form action="book" method="post">
    <div class="form-group">
        <input type="hidden" name="bookId" value="${book.id}">
        <label>Enter Title: </label>
        <input class="form-control" type="text" name="title" value="${book.title}">
        <label>Enter Author: </label>
        <input class="form-control" type="text" name="author" value="${book.author}">
        <label>Enter Category: </label>
        <input class="form-control" type="text" name="category" value="${book.category.id}">
        <label>Enter Publisher: </label>
        <input class="form-control" type="text" name="publisher" value="${book.publisher.id}">
        <label>Enter Price: </label>
        <input class="form-control" type="text" name="price" value="${book.price}">
    </div>
    <button type="submit" name="state" value="bookUpdate">Update</button>
</form>
</body>
</html>
