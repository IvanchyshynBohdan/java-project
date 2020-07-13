<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.07.2020
  Time: 23:23
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
<h2>Borrow Book</h2>
<form action="issue" method="post">
    <div class="form-group">
        <label>Book Title</label>
        <input class="form-control" type="text" name="title" value="${books.title}">
        <label>Member Name</label>
        <input class="form-control" type="text" name="surname" value="${member.lastName}">
        <label>Borrow Date</label>
        <input class="form-control" type="date" name="takenDate" value="${takenDate}">
        <label>Return Date</label>
        <input class="form-control" type="date" name="broughtDate" value="${broughtDate}">
        <input type="hidden" name="bookId" value="${books.id}">
        <button type="submit" name="state" value="issueConfirm">Submit</button>
    </div>
</form>
</body>
</html>
