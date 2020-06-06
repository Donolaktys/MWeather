package ru.donolaktys.mweather;

import ru.donolaktys.mweather.data.FavoriteCity;
import ru.donolaktys.mweather.interfaces.AdapterChangeable;
import ru.donolaktys.mweather.interfaces.IFavoriteRepository;

public class FavoriteCityLogic {
    private IFavoriteRepository repository;
    private AdapterChangeable adapter;           // Отправим сигнал изменения данных

    public FavoriteCityLogic(IFavoriteRepository repository){
        this.repository = repository;
    }

    public void setAdapter(AdapterChangeable adapter){
        this.adapter = adapter;
    }

    public void addCity(FavoriteCity favoriteCity){
        getRepository().add(favoriteCity);
        updateFavoriteCity();
    }

    public void editCity(FavoriteCity favoriteCity) {
        getRepository().update(favoriteCity);
        updateFavoriteCity();
    }

    public void deleteCity(FavoriteCity favoriteCity) {
        getRepository().delete(favoriteCity);
        updateFavoriteCity();
    }

    public void clearList() {
        getRepository().deleteAll();
        updateFavoriteCity();
    }

    private void updateFavoriteCity(){
        adapter.notifyDataChange();
    }

    public IFavoriteRepository getRepository() {
        return repository;
    }
}
