package servlets;

import models.Game;
import server.ServerContext;
import services.AccountService;
import services.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/games")
public class GameListServlet extends HttpServlet {
    private GameService gameService;
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<Game> games = gameService.findAll();

        req.setAttribute("games", games);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/games.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long gameId = Long.valueOf(req.getParameter("gameId"));
        Long userId = Long.valueOf(req.getParameter("userId"));
        gameService.addUserToGame(userId, gameId);
        resp.sendRedirect(req.getContextPath() + "/games");
    }

    @Override
    public void init() throws ServletException {
        gameService = ServerContext.getGameService();
        accountService = ServerContext.getAccountService();
    }
}
