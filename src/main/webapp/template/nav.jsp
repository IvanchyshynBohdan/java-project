<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.07.2020
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <c:choose>
        <c:when test="${sessionScope.member != null}">
            <ul class="nav justify-content-center" style="padding: 10px 0 10px 0">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.servletContext.contextPath}/member?state=memberInfo">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/issue?state=issueBorrow">Borrow
                        Book</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/issue?state=issueFind">Check
                        issues</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/member?state=memberSignOut">Log
                        out</a>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="nav justify-content-center" style="padding: 10px 0 10px 0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/librarian?state=librarianInfo">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/book?state=bookList">All
                        Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/librarian?state=librarianList">All
                        Librarians</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/member?state=memberList">All
                        Members</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.servletContext.contextPath}/issue?state=issueList">Borrows</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.servletContext.contextPath}/librarian?state=librarianSignOut">Log out</a>
                </li>
            </ul>
        </c:otherwise>
    </c:choose>
</nav>
