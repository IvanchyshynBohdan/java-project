package com.library.servlet;

import com.library.dao.BookDao;
import com.library.dao.LibrarianDao;
import com.library.dao.MemberDao;
import com.library.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LibrarianServlet extends HttpServlet {
    private LibrarianDao librarianDao;
    private MemberDao memberDao;
    private BookDao bookDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("librarianInfo")) {
            librarianInfo(request, response);
        } else if (state.equals("librarianList")) {
            librarianList(request, response);
        } else if (state.equals("updateLibrarian")) {
            updateLibrarian(request, response);
        } else if (state.equals("deleteLibrarian")) {
            deleteLibrarian(request, response);
        } else if (state.equals("librarianSignOut")) {
            librarianSignOut(request, response);
        }

    }

    private void librarianInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Librarian librarian = (Librarian) session.getAttribute("librarian");
        if (librarian != null) {
            Librarian findLibrarian = librarianDao.findById(librarian.getId());
            request.setAttribute("librarian", findLibrarian);
            request.getRequestDispatcher("/views/librarian/librarianProfile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp");
        }
    }

    private void librarianSignOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("librarian", null);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void librarianList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Librarian> librarianList = librarianDao.findAll();
        request.setAttribute("librarians", librarianList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/librarian/allLibrarian.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer librarianId = Integer.parseInt(request.getParameter("librarianId"));
        librarianDao.delete(librarianId);
        List<Librarian> librarianList = librarianDao.findAll();
        request.setAttribute("librarians", librarianList);
        response.sendRedirect(request.getContextPath() + "/librarian?state=librarianList");
    }

    private void updateLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer librarianId = Integer.parseInt(request.getParameter("librarianId"));
        Librarian librarian = librarianDao.findById(librarianId);
        request.setAttribute("librarian", librarian);
        request.getRequestDispatcher("/views/librarian/updateLibrarian.jsp").forward(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Librarian librarian = new Librarian();
        Integer librarianId = Integer.parseInt(request.getParameter("librarianId"));
        String name = request.getParameter("firstName");
        String surname = request.getParameter("lastName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Integer experience = Integer.parseInt(request.getParameter("experience"));
        librarian.setId(librarianId);
        librarian.setFirstName(name);
        librarian.setLastName(surname);
        librarian.setPassword(password);
        librarian.setExperience(experience);
        librarian.setEmail(email);
        librarian.setPhone(phone);
        boolean updated = librarianDao.update(librarian);
        if (updated) {
            request.setAttribute("librarians", librarianDao.findAll());
            request.getRequestDispatcher("/views/librarian/allLibrarian.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/librarian/updateLibrarian.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("librarianUpdate")) {
            confirmUpdate(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        librarianDao = new LibrarianDao();
        memberDao = new MemberDao();
        bookDao = new BookDao();
    }
}
