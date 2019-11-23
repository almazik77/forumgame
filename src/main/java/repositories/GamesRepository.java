package repositories;

import models.Game;

import java.util.List;

public interface GamesRepository extends CrudRepository<Game, Long> {
    List<Game> find(Long userId, boolean tr);

    List<Long> findWhereMod(Long id);
}
