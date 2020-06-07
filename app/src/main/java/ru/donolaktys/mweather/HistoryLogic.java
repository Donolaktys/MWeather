package ru.donolaktys.mweather;

import ru.donolaktys.mweather.data.History;
import ru.donolaktys.mweather.interfaces.AdapterChangeable;
import ru.donolaktys.mweather.interfaces.IHistoryRepository;

public class HistoryLogic {
    private IHistoryRepository repository;
    private AdapterChangeable adapter;           // Отправим сигнал изменения данных

    public HistoryLogic(IHistoryRepository repository){
        this.repository = repository;
    }

    public void setAdapter(AdapterChangeable adapter){
        this.adapter = adapter;
    }

    public void addHistory(History history){
        getRepository().add(history);
        updateHistory();
    }

    public void editHistory(History history) {
        getRepository().update(history);
        updateHistory();
    }

    public void deleteHistory(History history) {
        getRepository().delete(history);
        updateHistory();
    }

    public void clearList() {
        getRepository().deleteAll();
        updateHistory();
    }

    private void updateHistory(){
        adapter.notifyDataChange();
    }

    public IHistoryRepository getRepository() {
        return repository;
    }
}
