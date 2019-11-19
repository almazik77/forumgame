package servlets;

import models.User;
import server.ServerContext;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "uploadServlet", urlPatterns = {"/profile"}, loadOnStartup = 1)
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L // 20 MB
)
public class ProfileServlet extends HttpServlet {
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = null;
        if (req.getParameter("userId") != null)
            userId = Long.valueOf(req.getParameter("userId"));
        if (userId == null && req.getSession().getAttribute("userId") != null) {
            userId = (Long) req.getSession().getAttribute("userId");
        }
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/games");
            return;
        }
        Optional<User> user = accountService.find(userId);
        if (user.isPresent()) {
            req.setAttribute("userLogin", user.get().getLogin());
            req.setAttribute("userId", user.get().getId());
            req.setAttribute("userAvatar", req.getContextPath() + "/userAvatar/" + user.get().getId() + ".jpg");
        } else {
            resp.sendRedirect(req.getContextPath() + "/games");
            return;
        }
        req.getRequestDispatcher(req.getContextPath() + "/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        // gets absolute path of the web application
        String applicationPath = req.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + "userAvatar";
        // creates upload folder if it does not exists
        File uploadFolder = new File(uploadFilePath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        // write all files in upload folder
        for (Part part : req.getParts()) {
            if (part != null && part.getSize() > 0) {
                String fileName = ((req.getSession().getAttribute("userId")).toString()) + ".jpg";
                part.write(uploadFilePath + File.separator + fileName);
            }

        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }

    @Override
    public void init() throws ServletException {
        this.accountService = ServerContext.getAccountService();
    }
}
