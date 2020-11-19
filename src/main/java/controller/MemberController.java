package controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import domain.Member;
import repository.MemberDAOImpl;

@WebServlet(urlPatterns = {"/member/signin.do", "/member/signout.do"}, name = "MemberController")
public class MemberController extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // 있는 경우 반환, 없는 경우 만들어서 반환
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);

        MemberDAOImpl dao = new MemberDAOImpl();
        if(action.equals("signin.do")) {
            session.setAttribute("login", null);
            String email = request.getParameter("email");
            String pw = request.getParameter("pw");
            String checked = request.getParameter("checked");

            Member member = new Member(); // 전송하기 위한
            member.setEmail(email);
            member.setPw(pw);

            Member m = null; // 데이터베이스에서 가져온 레코드를 객체에 저장
            if((m = dao.read(member)) != null && pw.equals(m.getPw())) {
                //로그인 성공
                session.setAttribute("login", m);
                if (checked != null && checked.equals("yes")) {
                    Cookie cookie_id = new Cookie("email", email);
                    response.addCookie(cookie_id);
                } else {
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("email")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                    }
                }
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            }else {
                //로그인 실패
                request.setAttribute("message", "아이디 또는 암호를 확인하십시요");
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
                //response.sendRedirect("../member/signin-error.jsp");
            }
        }

        if(action.equals("signout.do")) {
            session.invalidate();
            response.sendRedirect("../main/index.jsp");
        }

    }
}
