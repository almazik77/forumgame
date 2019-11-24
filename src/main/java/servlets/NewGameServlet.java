package servlets;

import server.ServerContext;
import services.AccountService;
import services.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newGame")
public class NewGameServlet extends HttpServlet {
    private AccountService accountService;
    private GameService gameService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long userId = (Long) req.getSession().getAttribute("userId");

        if (userId == null) {

            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("userId")) {
                    userId = Long.valueOf(cookie.getValue());
                    break;
                }
            }
            if (userId == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }

        if (!accountService.checkIfCanCreateGame(userId)) {
            resp.sendRedirect(req.getContextPath() + "/games");
            return;
        }

        req.getRequestDispatcher(req.getContextPath() + "/jsp/newGame.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long moderatorId = (Long) req.getSession().getAttribute("userId");
        String gameName = req.getParameter("game_name");
        String gameDescription = req.getParameter("game_description");

        gameService.save(moderatorId, gameDescription, gameName);
        resp.sendRedirect(req.getContextPath() + "/games");
    }

    @Override
    public void init() throws ServletException {
        this.accountService = ServerContext.getAccountService();
        this.gameService = ServerContext.getGameService();
    }
}
