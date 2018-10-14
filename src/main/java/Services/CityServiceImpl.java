package Services;

import dao.CityDao;
import dao.CityDaoImpl;
import pojo.City;

public class CityServiceImpl implements CityService {
    CityDao cityDao = new CityDaoImpl();

    @Override
    public City getCityByName(String name) {
        return cityDao.getCityByName(name);
    }

    @Override
    public boolean addCity(City city) {
        if (cityDao.addCity(city)) {
            return true;
        }
        return false;
    }
}
