package ru.donolaktys.mweather.ui.favorite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.adapters.FavoriteAdapter;

public class FavoriteListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_favorite, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.fragmentViewFavorite);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        FavoriteAdapter adapter = new FavoriteAdapter();
        recyclerView.setAdapter(adapter);
        return root;
    }
}
