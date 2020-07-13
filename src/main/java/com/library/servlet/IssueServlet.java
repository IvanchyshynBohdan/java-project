package com.library.servlet;

import com.library.dao.BookDao;
import com.library.dao.IssuesDao;
import com.library.dao.MemberDao;
import com.library.model.Books;
import com.library.model.Issues;
import com.library.model.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IssueServlet extends HttpServlet {
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private IssuesDao issuesDao;
    private MemberDao memberDao;
    private BookDao bookDao;

    @Override
    public void init() throws ServletException {
        issuesDao = new IssuesDao();
        memberDao = new MemberDao();
        bookDao = new BookDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("issueFind")) {
            issueFind(request, response);
        } else if (state.equals("issueBorrow")) {
            issueBorrow(request, response);
        } else if (state.equals("issueLend")) {
            issueLend(request, response);
        } else if (state.equals("issueList")) {
            issueList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("issueConfirm")) {
            try {
                issueConfirm(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void issueList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Issues> issuesList = issuesDao.findAll();
        request.setAttribute("issues", issuesList);
        request.getRequestDispatcher("/views/issue/issueList.jsp").forward(request, response);
    }

    private void issueFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        List<Issues> issuesList = issuesDao.findByMember(member.getId());
        request.setAttribute("issues", issuesList);
        request.getRequestDispatcher("/views/issue/issueFind.jsp").forward(request, response);
    }

    private void issueBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Books> booksList = bookDao.findAll();
        request.setAttribute("books", booksList);
        request.getRequestDispatcher("/views/issue/issueBorrow.jsp").forward(request, response);
    }

    private void issueLend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Books books = bookDao.findById(bookId);
        request.setAttribute("books", books);
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        request.setAttribute("member", member);
        request.setAttribute("takenDate", formatter.format(new Date()));
        request.getRequestDispatcher("/views/issue/issueLend.jsp").forward(request, response);
    }

    private void issueConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, ParseException {
        Issues issues = new Issues();
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Books books = bookDao.findById(bookId);
        issues.setBooks(books);
        Member member = (Member) request.getSession().getAttribute("member");
        issues.setMember(member);
        String takenDate = request.getParameter("takenDate");
        String broughtDate = request.getParameter("broughtDate");
        issues.setTakenDate(formatter.parse(takenDate));
        issues.setBroughtDate(formatter.parse(broughtDate));
        issuesDao.create(issues);
        response.sendRedirect(request.getContextPath() + "/member?state=memberInfo");

    }
}
