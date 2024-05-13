package og.dz11.services;

import og.dz11.models.City;

import java.util.List;

public interface CityService {
    List<City> GetAll();

    City GetById(Long cityId);

    City Add(City city);

    City Update(City city);

    void Delete(Long cityId);
}
