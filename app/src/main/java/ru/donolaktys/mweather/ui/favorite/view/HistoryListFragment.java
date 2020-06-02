package ru.donolaktys.mweather.ui.favorite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.adapters.DayViewAdapter;
import ru.donolaktys.mweather.adapters.HistoryAdapter;

public class HistoryListFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_history, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.fragmentViewHistory);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        HistoryAdapter adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);
        return root;
    }
}
