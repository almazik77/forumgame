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

@WebServlet("/myGames")
public class UserGameListServlet extends HttpServlet {
    private GameService gameService;
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Long userId = Long.valueOf(req.getSession().getAttribute("userId").toString());

        List<Game> userGames = gameService.find(userId, true);

        req.setAttribute("games", userGames);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/games.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        gameService = ServerContext.getGameService();
        accountService = ServerContext.getAccountService();
    }
}
