package og.dz11.services;

import lombok.RequiredArgsConstructor;
import og.dz11.models.City;
import og.dz11.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public List<City> GetAll() {
        return cityRepository.findAll();
    }

    @Override
    public City GetById(Long cityId) {
        return cityRepository.FindCityById(cityId);
    }

    @Override
    public City Add(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City Update(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void Delete(Long cityId) {
        cityRepository.deleteById(cityId);
    }
}
