package services;

import models.Game;
import models.Phrase;
import repositories.GamesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GameService {
    private GamesRepository gamesRepository;

    public GameService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public List<Game> findAll() {
        return gamesRepository.findAll();
    }

    public List<Game> find(Long userId, boolean b) {
        return gamesRepository.find(userId, b);
    }

    public List<Phrase> find(Long gameId) {
        Optional<Game> game = gamesRepository.find(gameId);
        if (game.isPresent())
            return game.get().getGameText();
        else return Collections.EMPTY_LIST;
    }

    public void save(Long moderatorId, String gameDescription, String gameName) {
        gamesRepository.save(new Game(gameName, new ArrayList<>(), moderatorId, new ArrayList<>(), new ArrayList<>(), gameDescription));
    }

    public void update(String newPhrase, Long userId, Long gameId) {
        Optional<Game> game = gamesRepository.find(gameId);
        if (game.isPresent()) {
            game.get().getGameText().add(new Phrase(game.get().getGameText().size(), newPhrase, false, userId));
            gamesRepository.update(game.get());
        }
    }
}
