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
    private Integer pageLimit;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (req.getSession().getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Long userId = Long.valueOf(req.getSession().getAttribute("userId").toString());


        Integer page = null;
        if (req.getParameter("page") != null)
            page = Integer.valueOf(req.getParameter("page"));
        if (page == null || page <= 0) {
            page = 1;
        }


        List<Game> userGames = gameService.find(userId, true);
        Integer gamesCount = userGames.size();

        userGames = userGames.subList(Math.max(0, Math.min((page - 1) * pageLimit, userGames.size() - 1)),
                Math.min(page * pageLimit, userGames.size()));

        req.setAttribute("my", Boolean.TRUE);
        req.setAttribute("games", userGames);
        req.setAttribute("pagesCount", (gamesCount + pageLimit - 1) / pageLimit);
        req.getRequestDispatcher(req.getContextPath() + "/jsp/games.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        gameService = ServerContext.getGameService();
        accountService = ServerContext.getAccountService();
        pageLimit = ServerContext.getPageLimit();
    }
}
