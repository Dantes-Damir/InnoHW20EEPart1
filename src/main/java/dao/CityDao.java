package dao;

import pojo.City;

public interface CityDao {
    public boolean addCity(City city);

    public City getCityById(int id);

    public boolean update(City city);

    public boolean deleteCityById(int id);

    public boolean deleteCityByName(City city);

    public City getCityByName(String name);

}
