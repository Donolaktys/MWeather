package ru.donolaktys.mweather.ui.favorite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.donolaktys.mweather.FavoriteCityLogic;
import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.adapters.FavoriteAdapter;
import ru.donolaktys.mweather.data.FavoriteCity;
import ru.donolaktys.mweather.data.RoomFavoriteRepository;
import ru.donolaktys.mweather.interfaces.IFavoriteRepository;

public class FavoriteListFragment extends Fragment {
    private FavoriteCityLogic favoriteCityLogic;
    private IFavoriteRepository favoriteRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_favorite, container, false);
        favoriteRepository = new RoomFavoriteRepository();
        favoriteCityLogic = new FavoriteCityLogic(favoriteRepository);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.fragmentViewFavorite);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        FavoriteAdapter adapter = new FavoriteAdapter(favoriteCityLogic.getRepository());
        recyclerView.setAdapter(adapter);

        adapter.setOnMenuItemClickListener(new FavoriteAdapter.OnMenuItemClickListener() {
            @Override
            public void onItemDeleteClick(FavoriteCity favoriteCity) {
                favoriteCityLogic.deleteCity(favoriteCity);
            }
        });
        return root;
    }
}
