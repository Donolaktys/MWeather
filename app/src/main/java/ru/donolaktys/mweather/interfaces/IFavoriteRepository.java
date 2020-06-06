package ru.donolaktys.mweather.interfaces;

import ru.donolaktys.mweather.data.FavoriteCity;

public interface IFavoriteRepository {
    FavoriteCity add(FavoriteCity favoriteCity);
    void update(FavoriteCity favoriteCity);
    FavoriteCity getFavoriteByCity(String city);
    FavoriteCity getFavoriteById(long id);
    void delete(FavoriteCity favoriteCity);
    void deleteAll();
    int getCount();
}
