package servlets;

import models.Game;
import models.Phrase;
import server.ServerContext;
import services.AccountService;
import services.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService;
    private AccountService accountService;
    private Integer pageLimit;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

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
        Optional<Game> game = gameService.find(gameId);
        if (game.isPresent()) {
            if (!game.get().getPlayersId().contains(req.getSession().getAttribute("userId"))) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            List<Phrase> phrases = game.get().getGameText();
            List<Long> playersId = game.get().getPlayersId();
            req.setAttribute("playersId", playersId);
            List<String> playersLogin = new ArrayList<>();
            for (Long id : playersId) {
                playersLogin.add(accountService.find(id).get().getLogin());
            }

            List<String> statuses = game.get().getPlayerStatus();
            Integer page = null;
            if (req.getParameter("page") != null) {
                page = Integer.valueOf(req.getParameter("page"));
            }
            if (page == null || page <= 0) {
                page = 1;
            }
            req.getSession().setAttribute("gameId", gameId);
            req.setAttribute("statuses", statuses);
            Integer pageCount = (phrases.size() + pageLimit - 1) / pageLimit;
            req.setAttribute("pagesCount", pageCount);
            phrases = phrases.subList(Math.max(0, Math.min((page - 1) * pageLimit, phrases.size() - 1)),
                    Math.min(page * pageLimit, phrases.size()));

            if (game.get().getModeratorId().equals(req.getSession().getAttribute("userId")) && page.equals(pageCount))
                req.setAttribute("is_moderator", Boolean.TRUE);

            req.setAttribute("phrases", phrases);

            if (phrases.size() == 0 || (phrases.get(phrases.size() - 1).isChecked() && page.equals(pageCount))) {
                req.setAttribute("canAdd", Boolean.TRUE);
            } else {
                req.setAttribute("canAdd", Boolean.FALSE);
            }
            if (game.get().getModeratorId().equals(req.getSession().getAttribute("userId")) && page.equals(pageCount)) {
                req.setAttribute("canCheck", Boolean.TRUE);
            } else {
                req.setAttribute("canCheck", Boolean.FALSE);
            }
            req.getRequestDispatcher(req.getContextPath() + "/jsp/game.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/games");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long userId = (Long) req.getSession().getAttribute("userId");
        Long gameId = (Long) (req.getSession().getAttribute("gameId"));
        if (req.getParameter("checkLastMessage") != null) {
            gameService.setLastMessageChecked(gameId);
        } else {
            String newPhrase = req.getParameter("newPhrase");
            if (newPhrase != null) {
                gameService.update(newPhrase, userId, gameId);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/game?gameId=" + gameId + "&page=" + ((gameService.find(gameId).get().getGameText().size() + pageLimit - 1) / pageLimit));
    }

    @Override
    public void init() throws ServletException {
        this.gameService = ServerContext.getGameService();
        this.accountService = ServerContext.getAccountService();
        this.pageLimit = 5;
    }
}
