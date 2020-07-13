package com.library.servlet;

import com.library.dao.MemberDao;
import com.library.model.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MemberServlet extends HttpServlet {
    MemberDao memberDao;

    @Override
    public void init() throws ServletException {
        memberDao = new MemberDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("memberInfo")) {
            memberInfo(request, response);
        } else if (state.equals("memberList")) {
            memberList(request, response);
        } else if (state.equals("deleteMember")) {
            deleteMember(request, response);
        } else if (state.equals("updateMember")) {
            updateMember(request, response);
        } else if (state.equals("memberRegister")) {
            request.getRequestDispatcher("/views/member/memberRegister.jsp").forward(request, response);
        } else if (state.equals("memberSignOut")) {
            memberSignOut(request, response);
        }

    }

    private void memberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Member member = (Member) httpSession.getAttribute("member");
        if (member != null) {
            Member findMember = memberDao.findById(member.getId());
            request.setAttribute("member", findMember);
            request.getRequestDispatcher("/views/member/memberProfile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp");
        }
    }

    private void memberSignOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("member", null);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> memberList = memberDao.findAll();
        request.setAttribute("members", memberList);
        request.getRequestDispatcher("/views/member/memberList.jsp").forward(request, response);
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer memberId = Integer.parseInt(request.getParameter("memberId"));
        memberDao.delete(memberId);
        List<Member> memberList = memberDao.findAll();
        request.setAttribute("members", memberList);
        response.sendRedirect(request.getContextPath() + "/member?state=memberList");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer memberId = Integer.parseInt(request.getParameter("memberId"));
        Member member = memberDao.findById(memberId);
        request.setAttribute("member", member);
        request.getRequestDispatcher("/views/member/memberUpdate.jsp").forward(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = new Member();
        Integer memberId = Integer.parseInt(request.getParameter("memberId"));
        String name = request.getParameter("firstName");
        String surname = request.getParameter("lastName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        member.setId(memberId);
        member.setFirstName(name);
        member.setLastName(surname);
        member.setPassword(password);
        member.setCity(city);
        member.setEmail(email);
        member.setPhone(phone);
        boolean updated = memberDao.update(member);
        if (updated) {
            request.setAttribute("members", memberDao.findAll());
            request.getRequestDispatcher("/views/member/memberList.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/member/memberUpdate.jsp").forward(request, response);
        }
    }

    private void memberRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = new Member();
        member.setFirstName(request.getParameter("name"));
        member.setLastName(request.getParameter("surname"));
        member.setEmail(request.getParameter("email"));
        member.setPhone(request.getParameter("phone"));
        member.setCity(request.getParameter("city"));
        member.setPassword(request.getParameter("password"));
        if (memberDao.create(member) != null) {
            request.getRequestDispatcher("/views/member/memberLogin.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/member/memberRegister.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("memberUpdate")) {
            confirmUpdate(request, response);
        } else if (state.equals("memberRegister")) {
            memberRegister(request, response);
        }
    }
}
