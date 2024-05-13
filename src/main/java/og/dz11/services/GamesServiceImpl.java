package og.dz11.services;

import lombok.RequiredArgsConstructor;
import og.dz11.models.Games;
import og.dz11.repositories.GamesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService {
    private final GamesRepository gamesRepository;

    @Override
    public List<Games> GetAll() {
        return gamesRepository.findAll();
    }

    @Override
    public Games GetById(Long gamesId) {
        return gamesRepository.findById(gamesId).orElse(null);
    }

    @Override
    public Games Add(Games event) {
        return gamesRepository.save(event);
    }

    @Override
    public Games Update(Games games) {
        return gamesRepository.save(games);
    }

    @Override
    public void Delete(Long gamesId) {
        gamesRepository.deleteById(gamesId);
    }
}
