package repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Game;
import models.Phrase;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GamesRepositoryJDBC implements GamesRepository {

    private UsersRepository usersRepository;
    private ObjectMapper objectMapper;
    private Connection connection;

    //language=SQL
    private String SQL_INSERT_GAME = "INSERT INTO games " +
            "(name, phrases, moderator_id, player_id, player_status, description) " +
            "VALUES (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private String SQL_UPDATE_GAME = "UPDATE games set name = ?, phrases = ?, moderator_id = ?," +
            " player_id = ?, player_status = ?, description = ? where id = ?;";

    //language=SQL
    private String SQL_UPDATE_PHRASES = "update games set phrases = ? where id = ?;";

    //language=SQL
    private String SQL_UPDATE_STATUSES = "update games set players_status = ? where id = ?;";

    //language=SQL
    private String SQL_DELETE_GAME = "DELETE FROM games where id = ?;";

    //language=SQL
    private String SQL_FIND_GAME_BY_ID = "SELECT * from games where id = ?;";

    //language=SQL
    private String SQL_FIND_ALL = "SELECT * FROM games;";

    public GamesRepositoryJDBC(Connection connection, UsersRepository usersRepository, ObjectMapper objectMapper) {
        this.connection = connection;
        this.usersRepository = usersRepository;
        this.objectMapper = objectMapper;
    }

    private RowMapper<Game> gameRowMapper = row -> {
        try {
            String name = row.getString("name");
            Long id = row.getLong("id");
            ObjectMapper mapper = new ObjectMapper();
            List<Phrase> gameText = new ArrayList<>(Arrays.asList(mapper.readValue(row.getString("phrases"), Phrase[].class)));
            Long moderatorId = row.getLong("moderator_id");
            List<Long> playersId = new ArrayList<>(Arrays.asList(mapper.readValue(row.getString("player_id"), Long[].class)));
            List<String> playersStatus = new ArrayList<>(Arrays.asList(mapper.readValue(row.getString("player_status"), String[].class)));
            String description = row.getString("description");
            return new Game(id, name, gameText, moderatorId, playersId, playersStatus, description);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public void save(Game model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_GAME,
                    Statement.RETURN_GENERATED_KEYS);
            setStatement(model, statement);

            statement.execute();
            statement.close();

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void setStatement(Game model, PreparedStatement statement) throws SQLException, JsonProcessingException {
        statement.setString(1, model.getName());
        statement.setString(2, objectMapper.writeValueAsString(model.getGameText()));
        statement.setLong(3, model.getModeratorId());
        statement.setString(4, objectMapper.writeValueAsString(model.getPlayersId()));
        statement.setString(5, objectMapper.writeValueAsString(model.getPlayerStatus()));
        statement.setString(6, model.getDescription());
    }

    @Override
    public void update(Game model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_GAME, Statement.RETURN_GENERATED_KEYS);

            setStatement(model, statement);

            statement.setLong(7, model.getId());

            statement.execute();

            statement.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_GAME, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<Game> find(Long id) {
        Game game = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_GAME_BY_ID, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                game = gameRowMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return Optional.ofNullable(game);
    }

    @Override
    public List<Game> findAll() {
        List<Game> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(gameRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

    @Override
    public List<Game> find(Long userId, boolean tr) {
        List<Game> result = new ArrayList<>();
        List<Game> allGames = findAll();
        for (Game game : allGames) {
            if (game.getPlayersId().contains(userId)) {
                result.add(game);
            }
        }
        return result;

    }

    @Override
    public List<Long> findWhereMod(Long id) {
        List<Long> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM games WHERE moderator_id = " + id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(gameRowMapper.mapRow(resultSet).getId());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }
}
