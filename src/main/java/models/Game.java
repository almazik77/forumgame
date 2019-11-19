package models;

import java.util.List;

public class Game {
    private Long id;
    private String name;
    private List<Phrase> gameText;
    private Long moderatorId;
    private List<Long> playersId;
    private List<String> playerStatus;
    private String description;

    public Game() {
    }

    public Game(Long id, String name, List<Phrase> gameText, Long moderatorId, List<Long> playersId, List<String> playerStatus, String description) {
        this.id = id;
        this.name = name;
        this.gameText = gameText;
        this.moderatorId = moderatorId;
        this.playersId = playersId;
        this.playerStatus = playerStatus;
        this.description = description;
    }

    public Game(String name, List<Phrase> gameText, Long moderatorId, List<Long> playersId, List<String> playerStatus, String description) {
        this.name = name;
        this.gameText = gameText;
        this.moderatorId = moderatorId;
        this.playersId = playersId;
        this.playerStatus = playerStatus;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Phrase> getGameText() {
        return gameText;
    }

    public void setGameText(List<Phrase> gameText) {
        this.gameText = gameText;
    }

    public Long getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(Long moderatorId) {
        this.moderatorId = moderatorId;
    }

    public List<Long> getPlayersId() {
        return playersId;
    }

    public void setPlayersId(List<Long> playersId) {
        this.playersId = playersId;
    }

    public List<String> getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(List<String> playerStatus) {
        this.playerStatus = playerStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
