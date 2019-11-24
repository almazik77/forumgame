package services;

import models.Game;
import models.Phrase;
import repositories.GamesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameService {
    private GamesRepository gamesRepository;
    private AccountService accountService;

    public GameService(GamesRepository gamesRepository, AccountService accountService) {
        this.gamesRepository = gamesRepository;
        this.accountService = accountService;
    }

    public List<Game> findAll() {
        List<Game> games =
                gamesRepository.findAll();
        for (Game game : games) {
            game.setModeratorLogin(accountService.find(game.getModeratorId()).get().getLogin());
        }
        return games;
    }

    public List<Game> find(Long userId, boolean b) {
        List<Game> games =
                gamesRepository.find(userId, b);
        for (Game game : games) {
            game.setModeratorLogin(accountService.find(game.getModeratorId()).get().getLogin());
        }
        return games;
    }

    public Optional<Game> find(Long gameId) {
        Optional<Game> game = gamesRepository.find(gameId);
        if (game.isPresent()) {
            game.get().setModeratorLogin(accountService.find(game.get().getModeratorId()).get().getLogin());
        }
        return gamesRepository.find(gameId);
    }

    public void save(Long moderatorId, String gameDescription, String gameName) {
        List<Long> players = new ArrayList<>();
        players.add(moderatorId);
        List<String> statuses = new ArrayList<>();
        statuses.add("Alive");
        gamesRepository.save(new Game(gameName, new ArrayList<Phrase>(), moderatorId, players, statuses, gameDescription));
    }

    public void update(String newPhrase, Long userId, Long gameId) {
        String userLogin = accountService.find(userId).get().getLogin();
        Optional<Game> game = gamesRepository.find(gameId);
        if (game.isPresent()) {
            game.get().getGameText().add(new Phrase(game.get().getGameText().size(), newPhrase, false, userId, userLogin));
            gamesRepository.update(game.get());
        }
    }

    public void addUserToGame(Long userId, Long gameId) {
        Optional<Game> game = gamesRepository.find(gameId);
        if (game.isPresent()) {
            List<Long> pl = game.get().getPlayersId();
            if (!pl.contains(userId)) {
                pl.add(userId);
                game.get().getPlayerStatus().add("Alive");
            }
            gamesRepository.update(game.get());
        }
    }

    public void setLastMessageChecked(Long gameId) {
        Optional<Game> game = gamesRepository.find(gameId);
        if (game.isPresent()) {
            List<Phrase> phrases = game.get().getGameText();
            if (phrases.size() > 0)
                phrases.get(phrases.size() - 1).setChecked(true);
            gamesRepository.update(game.get());
        }
    }
}
