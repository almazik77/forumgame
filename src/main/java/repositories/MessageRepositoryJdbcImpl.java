package repositories;

import models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessageRepository {
    private Connection connection;

    //language=SQL
    private final String SQL_INSERT_MESSAGE = "INSERT into message (fromuserid, touserid, message) VALUES " +
            "(?, ?, ?);";
    //language=SQL
    private final String SQL_FIND_MESSAGES_BETWEEN_TWO_PEOPLE = "SELECT * FROM message " +
            "where (fromuserid = ? and  touserid = ?) or (touserid = ? and fromuserid = ?)";

    //language=SQL
    private final String SQL_FIND_SENDER_IDs_BY_RECIEVER_ID = "SELECT DISTINCT fromuserid FROM message " +
            " WHERE touserid = ?";

    public MessageRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }


    private RowMapper<Long> idRowMapper = row -> {
        Long id = row.getLong("fromuserid");
        return id;
    };

    private RowMapper<Message> messageRowMapper = row -> {
        try {
            Long id = row.getLong("id");
            Long fromUserId = row.getLong("fromuserid");
            Long toUserId = row.getLong("toUserId");
            String message = row.getString("message");
            return Message.builder().fromUserId(fromUserId).id(id).toUserId(toUserId).message(message).build();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public void save(Message model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MESSAGE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, model.getFromUserId());
            statement.setLong(2, model.getToUserId());
            statement.setString(3, model.getMessage());
            statement.execute();
            statement.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Message model) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<Message> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public List<Message> messagesBetweenTwoPerson(Long firstId, Long secondId) {
        List<Message> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_MESSAGES_BETWEEN_TWO_PEOPLE, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, firstId);
            statement.setLong(3, firstId);
            statement.setLong(2, secondId);
            statement.setLong(4, secondId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = messageRowMapper.mapRow(resultSet);
                result.add(message);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

    @Override
    public List<Long> senderIDsTo(Long id) {
        List<Long> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_SENDER_IDs_BY_RECIEVER_ID, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(idRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }
}
