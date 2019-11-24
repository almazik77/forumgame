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
    private Integer pageLimit;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer page = null;
        if (req.getParameter("page") != null)
            page = Integer.valueOf(req.getParameter("page"));
        if (page == null || page <= 0) {
            page = 1;
        }

        List<Game> games = gameService.findAll();
        Integer gamesCount = games.size();
        games = games.subList(Math.max(0, Math.min((page - 1) * pageLimit, games.size() - 1)), Math.min(page * pageLimit, games.size()));

        req.setAttribute("games", games);
        req.setAttribute("pagesCount", (gamesCount + pageLimit - 1) / pageLimit);
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
        pageLimit = ServerContext.getPageLimit();
    }
}
