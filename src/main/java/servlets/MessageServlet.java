package servlets;

import models.Message;
import server.ServerContext;
import services.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/messages")
public class MessageServlet extends HttpServlet {
    private MessageService messageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (req.getSession().getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        if (req.getParameter("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/im");
            return;
        }
        Long first = Long.valueOf(req.getParameter("userId"));
        Long second = (Long) req.getSession().getAttribute("userId");

        List<Message> messages = messageService.messagesBetweenTwoPerson(first, second);

        req.setAttribute("messages", messages);
        req.setAttribute("toUser", first);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/messages.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long from = (Long) req.getSession().getAttribute("userId");
        Long to = Long.valueOf(req.getParameter("userId"));
        String message = req.getParameter("message");
        messageService.save(message, from, to);
        resp.sendRedirect(req.getContextPath() + "/messages?userId=" + to);
    }

    @Override
    public void init() throws ServletException {
        messageService = ServerContext.getMessageService();
    }
}
