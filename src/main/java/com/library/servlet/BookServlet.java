package com.library.servlet;

import com.library.dao.BookDao;
import com.library.model.Books;
import com.library.model.Category;
import com.library.model.Member;
import com.library.model.Publisher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {

    BookDao bookDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("bookList")) {
            bookList(request, response);
        } else if (state.equals("deleteBook")) {
            deleteBook(request, response);
        } else if (state.equals("updateBook")) {
            updateBook(request, response);
        }
    }

    private void bookList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Books> booksList = bookDao.findAll();
        request.setAttribute("books", booksList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/book/allBook.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        bookDao.delete(bookId);
        List<Books> books = bookDao.findAll();
        request.setAttribute("books", books);
        response.sendRedirect(request.getContextPath() + "/book?state=bookList");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Books book = bookDao.findById(bookId);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/views/book/updateBook.jsp").forward(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Books book = new Books();
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Integer publisherId = Integer.parseInt(request.getParameter("publisher"));
        Float price = Float.parseFloat(request.getParameter("price"));
        book.setId(bookId);
        book.setTitle(title);
        book.setAuthor(author);
        Category category = new Category();
        category.setId(categoryId);
        book.setCategory(category);
        Publisher publisher = new Publisher();
        publisher.setId(publisherId);
        book.setPublisher(publisher);
        book.setPrice(price);
        boolean updated = bookDao.update(book);
        if (updated) {
            request.setAttribute("books", bookDao.findAll());
            request.getRequestDispatcher("/views/book/allBook.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/book/updateBook.jsp").forward(request, response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("bookUpdate")) {
            confirmUpdate(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        bookDao = new BookDao();
    }
}
