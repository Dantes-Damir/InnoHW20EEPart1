package Services;

import pojo.City;

public interface CityService {
    public City getCityByName(String name);

    public boolean addCity(City city);
}
