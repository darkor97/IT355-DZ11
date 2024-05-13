package og.dz11.repositories;

import og.dz11.models.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GamesRepository extends JpaRepository<Games, Long> {

    @Query(value = "SELECT COUNT(*) FROM OLYMPICS.GAMES", nativeQuery = true)
    int CountAllGames();

    @Query(value = "SELECT * FROM OLYMPICS.GAMES WHERE GAMES_YEAR = :year", nativeQuery = true)
    Games FindByYear(Integer year);

    List<Games> findByNameIsContaining(String Name);

    List<Games> findByYearAfter(Integer fromYear);

    List<Games> findBySeason(String season);

}
