package servlets;

import models.Phrase;
import server.ServerContext;
import services.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null && req.getSession().getAttribute("gameId") == null) {
            resp.sendRedirect(req.getContextPath() + "/games");
            return;
        }
        if (req.getSession().getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Long gameId = null;

        if (req.getParameter("id") == null) {
            gameId = (Long) req.getSession().getAttribute("gameId");
        } else {
            gameId = Long.valueOf(req.getParameter("id"));
        }

        if (gameId == null) {
            resp.sendRedirect(req.getContextPath() + "/profile");
            return;
        }
        List<Phrase> phrases = gameService.find(gameId);
        req.getSession().setAttribute("gameId", gameId);
        req.setAttribute("phrases", phrases);

        req.getRequestDispatcher(req.getContextPath() + "/jsp/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPhrase = req.getParameter("newPhrase");
        Long userId = (Long) req.getSession().getAttribute("userId");
        Long gameId = (Long) (req.getSession().getAttribute("gameId"));
        if (newPhrase != null) {
            gameService.update(newPhrase, userId, gameId);
        }
        resp.sendRedirect(req.getContextPath() + "/game?gameId=" + gameId);
    }

    @Override
    public void init() throws ServletException {
        this.gameService = ServerContext.getGameService();
    }
}
