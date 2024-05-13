package og.dz11.repositories;

import og.dz11.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM OLYMPICS.City", nativeQuery = true)
    List<City> ReadAllCities();

    @Query(value = "SELECT * FROM OLYMPICS.CITY WHERE id = :id", nativeQuery = true)
    City FindCityById(@Param("id") Long id);

    @Query(value = "SELECT TOP(1) * FROM OLYMPICS.CITY WHERE CITY_NAME LIKE %:name%", nativeQuery = true)
    City FindByName(@Param("name") String name);

    @Query(value = "SELECT COUNT (*) FROM OLYMPICS.CITY JOIN OLYMPICS.GAMES_CITY ON CITY.ID = GAMES_CITY.CITY_ID WHERE CITY_ID = :id", nativeQuery = true)
    int CountGamesByCity(@Param("id") Long id);

    @Query(value = "SELECT OLYMPICS.CITY.*, OLYMPICS.GAMES.ID AS games_id, OLYMPICS.GAMES.GAMES_YEAR, OLYMPICS.GAMES.GAMES_NAME  FROM OLYMPICS.CITY JOIN OLYMPICS.GAMES ON CITY.ID = GAMES.ID WHERE SEASON = :season", nativeQuery = true)
    List<City> GetCitiesBySeason(@Param("season") String season);
}
