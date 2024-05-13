package og.dz11.services;

import og.dz11.models.Games;

import java.util.List;

public interface GamesService {
    List<Games> GetAll();

    Games GetById(Long gamesId);

    Games Add(Games event);

    Games Update(Games games);

    void Delete(Long gamesId);
}
