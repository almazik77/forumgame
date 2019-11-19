package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repositories.GamesRepository;
import repositories.GamesRepositoryJDBC;
import repositories.UsersRepository;
import repositories.UsersRepositoryJDBC;
import services.AccountService;
import services.GameService;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ServerContext {

    static Connection connection;
    static UsersRepository usersRepository;
    static GamesRepository gamesRepository;
    static AccountService accountService;
    static GameService gameService;

    public static GameService getGameService() {
        if (gameService == null) {
            gameService = new GameService(getGamesRepository());
        }
        return gameService;
    }

    public static AccountService getAccountService() {
        if (accountService == null) {
            accountService = new AccountService(getUsersRepository(), new BCryptPasswordEncoder());
        }
        return accountService;
    }

    public static UsersRepository getUsersRepository() {
        if (usersRepository == null) {
            usersRepository = new UsersRepositoryJDBC(getConnection());
        }
        return usersRepository;
    }

    public static GamesRepository getGamesRepository() {
        if (gamesRepository == null) {
            gamesRepository = new GamesRepositoryJDBC(getConnection(), getUsersRepository(), new ObjectMapper());
        }
        return gamesRepository;
    }


    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(new FileReader("/media/almaz/Data/projects/forumgame/db.properties"));
                String url = properties.getProperty("db.url");
                String userName = properties.getProperty("db.user_name");
                String password = properties.getProperty("db.password");


                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException | IOException | ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return connection;
    }
}
