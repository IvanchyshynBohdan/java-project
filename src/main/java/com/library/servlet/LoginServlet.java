package com.library.servlet;

import com.library.dao.LibrarianDao;
import com.library.dao.MemberDao;
import com.library.model.Librarian;
import com.library.model.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginServlet extends HttpServlet {

    MemberDao memberDao;
    LibrarianDao librarianDao;

    @Override
    public void init() throws ServletException {
        memberDao = new MemberDao();
        librarianDao = new LibrarianDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("memberLogin")) {
            request.getRequestDispatcher("/views/member/memberLogin.jsp").forward(request, response);
        } else if (state.equals("librarianLogin")) {
            request.getRequestDispatcher("/views/librarian/librarianLogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("memberLogin")) {
            memberLogin(request, response);
        } else if (state.equals("librarianLogin")) {
            librarianLogin(request, response);
        }
    }

    private void memberLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Member member = memberDao.validate(email, password);
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
            response.sendRedirect(request.getContextPath() + "/member?state=memberInfo");
        } else {
            request.getRequestDispatcher("/views/member/memberLogin.jsp").forward(request, response);
        }
    }

    private void librarianLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Librarian librarian = librarianDao.validate(email, password);
        if (librarian != null) {
            HttpSession session = request.getSession();
            session.setAttribute("librarian", librarian);
            response.sendRedirect(request.getContextPath() + "/librarian?state=librarianInfo");
        } else {
            request.getRequestDispatcher("/views/librarian/librarianLogin.jsp").forward(request, response);
        }
    }

}
