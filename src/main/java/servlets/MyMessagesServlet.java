package servlets;

import models.User;
import server.ServerContext;
import services.AccountService;
import services.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/im")
public class MyMessagesServlet extends HttpServlet {
    private AccountService accountService;
    private MessageService messageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        List<Long> ids = messageService.senderIDsTo(userId);
        List<User> from = new ArrayList<>();
        for (Long id : ids) {
            from.add(accountService.find(id).get());
        }
        req.setAttribute("from", from);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/myMessages.jsp").forward(req, resp);
    }


    @Override
    public void init() throws ServletException {
        this.accountService = ServerContext.getAccountService();
        this.messageService = ServerContext.getMessageService();
    }
}
