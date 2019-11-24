package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repositories.*;
import services.AccountService;
import services.GameService;
import services.MessageService;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ServerContext {

    static Connection connection;
    static UsersRepository usersRepository;
    static MessageRepository messageRepository;
    static GamesRepository gamesRepository;
    static AccountService accountService;
    static GameService gameService;
    static MessageService messageService;
    static Integer pageLimit = 5;


    public static Integer getPageLimit() {
        return pageLimit;
    }

    public static MessageService getMessageService() {
        if (messageService == null) {
            messageService = new MessageService(getMessageRepository());
        }
        return messageService;
    }

    public static MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            messageRepository = new MessageRepositoryJdbcImpl(getConnection());
        }
        return messageRepository;
    }

    public static GameService getGameService() {
        if (gameService == null) {
            gameService = new GameService(getGamesRepository(), getAccountService());
        }
        return gameService;
    }

    public static AccountService getAccountService() {
        if (accountService == null) {
            accountService = new AccountService(getUsersRepository(), getGamesRepository(), new BCryptPasswordEncoder());
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
                properties.load(new FileReader("D:\\projects\\forumgame\\db.properties"));

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
