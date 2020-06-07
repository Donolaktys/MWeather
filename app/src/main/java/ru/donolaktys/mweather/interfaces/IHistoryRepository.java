package ru.donolaktys.mweather.interfaces;

import ru.donolaktys.mweather.data.History;

public interface IHistoryRepository {
    History add(History history);
    void update(History history);
    History get(int position);
    void delete(History history);
    void deleteAll();
    int getCount();
}
