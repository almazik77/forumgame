package servlets;

import models.User;
import server.ServerContext;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("userLogin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
        User user = accountService.find((String) req.getSession().getAttribute("userLogin"));
        req.setAttribute("userMail", user.getMail());
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        this.accountService = ServerContext.getAccountService();
    }
}
