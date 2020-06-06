package ru.donolaktys.mweather.data;

import ru.donolaktys.mweather.MWeather;
import ru.donolaktys.mweather.interfaces.FavoriteDao;
import ru.donolaktys.mweather.interfaces.IFavoriteRepository;

public class RoomFavoriteRepository implements IFavoriteRepository {

    private FavoriteDao favoriteDao;

    public RoomFavoriteRepository() {
        this.favoriteDao = MWeather.getInstance().getFavoriteDao();
    }

    @Override
    public FavoriteCity add(FavoriteCity favoriteCity) {
        long id = favoriteDao.insertFavoriteCity(favoriteCity);
        favoriteCity.setId(id);
        return favoriteCity;
    }

    @Override
    public void update(FavoriteCity favoriteCity) {
        favoriteDao.update(favoriteCity);
    }

    @Override
    public FavoriteCity getFavoriteByCity(String city) {
        return favoriteDao.getFavoriteByCity(city);
    }

    @Override
    public FavoriteCity getFavoriteById(long id) {
        return favoriteDao.getFavoriteById(id);
    }

    @Override
    public void delete(FavoriteCity favoriteCity) {
        favoriteDao.delete(favoriteCity);
    }

    @Override
    public void deleteAll() {
        favoriteDao.deleteAll();
    }

    @Override
    public int getCount() {
        return favoriteDao.getCount();
    }
}
