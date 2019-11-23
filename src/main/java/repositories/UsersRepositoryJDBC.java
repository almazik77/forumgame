package repositories;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJDBC implements UsersRepository {

    private Connection connection;

    //language=SQL
    private String SQL_FIND_ACCOUNT_BY_LOGIN = "SELECT * FROM account WHERE login = ?";
    //language=SQL
    private String SQL_INSERT_ACCOUNT = "INSERT INTO account (login, password, email, icon_path) " +
            "VALUES (?,?,?, ?)";
    //language=SQL
    private String SQL_UPDATE_ACCOUNT = "UPDATE account set login = ?, password = ?, email = ?, icon_path = ? where id = ?";
    //language=SQL
    private String SQL_DELETE_ACCOUNT = "DELETE FROM account WHERE id = ?";
    //language=SQL
    private String SQL_FIND_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id = ?";
    //language=SQL
    private String SQL_FIND_ALL = "SELECT * FROM account";

    private RowMapper<User> userRowMapper = row -> {
        Long id = row.getLong("id");
        String login = row.getString("login");
        String password = row.getString("password");
        String mail = row.getString("email");
        String role = row.getString("role");
        String iconPath = row.getString("icon_path");
        return User.builder().id(id).email(mail).password(password).login(login).role(role).iconPath(iconPath).build();
    };

    public UsersRepositoryJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(User model) {
        try {
            // подготовим выражение для обращения к БД
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ACCOUNT,
                    Statement.RETURN_GENERATED_KEYS);
            //добавим данные в выражение
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPassword());
            if (model.getMail().isPresent())
                statement.setString(3, model.getMail().get());

            statement.setString(4, model.getIconPath());

            // обратимся к БД нашим выражением и получим сколько строк было затронуто
            int affectedRows = statement.executeUpdate();
            // если таких нет, значит есть проблемы с СБД
            if (affectedRows == 0) {
                throw new SQLException();
            }
            // ResultSet - интерфейс, который позволяет проходится по объектам, которые нам возвращает БД
            ResultSet generatesKeys = statement.getGeneratedKeys();
            // Проверяем вернула ли БД нам что-нибудь
            if (generatesKeys.next()) {
                model.setId(generatesKeys.getLong("id"));
            } else {
                throw new SQLException();
            }
            statement.close();
            generatesKeys.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User model) {
        try {
            // подготовим выражение для обращения к БД
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ACCOUNT,
                    Statement.RETURN_GENERATED_KEYS);
            //добавим данные в выражение
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPassword());
            if (model.getMail().isPresent())
                statement.setString(3, model.getMail().get());
            else {
                statement.setString(3, null);
            }
            statement.setString(4, model.getIconPath());
            statement.setLong(5, model.getId());
            // отравим выражение в СУБД и
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            // подготовим выражение для обращения к БД
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ACCOUNT,
                    Statement.RETURN_GENERATED_KEYS);
            //добавим данные в выражение
            statement.setLong(1, id);
            // отравим выражение в СУБД и
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> find(Long id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = userRowMapper.mapRow(resultSet);
            }
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try {
            // подготовим выражение для обращения к БД
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            // получим всех пользователей
            ResultSet resultSet = statement.executeQuery();
            // добавим всех пользователей в список
            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                result.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public Optional<User> find(String login) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_LOGIN, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = userRowMapper.mapRow(resultSet);
            }
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void makeAdmin(Long id) {
        try {
            // подготовим выражение для обращения к БД
            PreparedStatement statement = connection.prepareStatement("UPDATE account set role = 'admin' where id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            //добавим данные в выражение
            statement.setLong(1, id);
            // отравим выражение в СУБД и
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
