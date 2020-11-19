package controller;

import domain.Member;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/v1/member/signin", "/v1/member/signout"}, name = "MemberController")
public class MemberControllerV1 extends HttpServlet {
    Member registeredUser = null; // 등록된 사용자

    public void init(ServletConfig config) throws ServletException {
        registeredUser = new Member();
        registeredUser.setEmail("egyou@induk.ac.kr");
        registeredUser.setPw("cometrue");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // 있는 경우 반환, 없는 경우 만들어서 반환
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);

        if(action.equals("signin")) {
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            String checked = request.getParameter("checked");
            Member m = new Member();
            m.setEmail(id);
            m.setPw(pw);
            if(id.equals(registeredUser.getId()) && pw.equals(registeredUser.getPw())) {
                session.setAttribute("login", m); // login.id == login.getId()
                if (checked != null && checked.equals("yes")) {
                    Cookie cookie_id = new Cookie("id", id);
                    response.addCookie(cookie_id);
                } else {
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("id")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                    }
                }
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            }
            else {
                response.sendRedirect("../member/signin-form.jsp");
            }
        }

        if(action.equals("signout")) {
            session.invalidate();
            response.sendRedirect("../main/index.jsp");
        }
            System.out.println(uri);
    }
}
