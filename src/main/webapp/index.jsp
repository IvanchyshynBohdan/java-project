<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Website</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<nav>
    <ul class="nav justify-content-center" style="padding: 10px 0 10px 0">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.servletContext.contextPath}/login?state=memberLogin">User Login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.servletContext.contextPath}/login?state=librarianLogin">Librarian
                Login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.servletContext.contextPath}/member?state=memberRegister">
                Register</a>
        </li>
    </ul>
</nav>
<h1 style="text-align: center; margin-top: 20px">Welcome to Library Website!</h1>
</body>
</html>