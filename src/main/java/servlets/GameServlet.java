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
        if (req.getSession().getAttribute("gameId") == null) {
            resp.sendRedirect(req.getContextPath() + "/games");
            return;
        }
        Long gameId = (Long) req.getSession().getAttribute("gameId");
        List<Phrase> phrases = gameService.find(gameId);

        req.setAttribute("phrases", phrases);

        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }


    @Override
    public void init() throws ServletException {
        this.gameService = ServerContext.getGameService();
    }
}
