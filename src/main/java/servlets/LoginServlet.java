package servlets;

import server.ServerContext;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AccountService accountService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("userId")) {
                cookie = c;
                break;
            }
        }
        if (cookie != null || req.getSession().getAttribute("userId") != null) {
            resp.sendRedirect(req.getContextPath() + "/profile?userId=" + req.getParameter("userId"));
            return;
        }
        req.getRequestDispatcher(req.getContextPath() + "/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String res = accountService.find(login, password);
        if (res.equals("Success")) {
            Long userId = accountService.find(login).getId();
            if (req.getParameterValues("remember_me") != null) {
                resp.addCookie(new Cookie("userId", userId.toString()));
            }
            req.getSession().setAttribute("userId", userId);
            req.getSession().setAttribute("userAvatar", req.getContextPath() + "/userAvatar/" + userId + ".jpg");
            resp.sendRedirect(req.getContextPath() + "/profile");
            return;
        } else {
            req.setAttribute("error_msg", res);
            req.setAttribute("login", login);
        }
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        this.accountService = ServerContext.getAccountService();
    }
}
