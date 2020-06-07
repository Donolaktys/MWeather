package ru.donolaktys.mweather.data;

import ru.donolaktys.mweather.MWeather;
import ru.donolaktys.mweather.interfaces.HistoryDao;
import ru.donolaktys.mweather.interfaces.IHistoryRepository;

public class RoomHistoryRepository implements IHistoryRepository {

    private HistoryDao historyDao;

    public RoomHistoryRepository() {
        this.historyDao = MWeather.getInstance().getHistoryDao();
    }

    @Override
    public History add(History history) {
        long id = historyDao.insertHistory(history);
        history.setId(id);
        return history;
    }

    @Override
    public void update(History history) {
        historyDao.update(history);
    }

    @Override
    public History get(int position) {
        return historyDao.getHistory(position);
    }

    @Override
    public void delete(History history) {
        historyDao.delete(history);
    }

    @Override
    public void deleteAll() {
        historyDao.deleteAll();
    }

    @Override
    public int getCount() {
        return historyDao.getCount();
    }
}
