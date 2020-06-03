package ru.donolaktys.mweather.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.adapters.FavoriteAdapter;
import ru.donolaktys.mweather.data.FavoriteCity;
import ru.donolaktys.mweather.data.SearchHistory;
import ru.donolaktys.mweather.ui.favorite.view.FavoriteListFragment;
import ru.donolaktys.mweather.ui.favorite.view.HistoryListFragment;

public class FavoriteFragment extends Fragment {
    private FavoriteListFragment favoriteListFragment;
    private HistoryListFragment historyListFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        init();
        replaceFragment(R.id.content_favorite, favoriteListFragment);
        replaceFragment(R.id.content_history, historyListFragment);
        return root;
    }

    private void init() {
        favoriteListFragment = new FavoriteListFragment();
        historyListFragment = new HistoryListFragment();
    }

    private void replaceFragment(int id, Fragment fragment) {
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(id, fragment);
            fragmentTransaction.commit();
    }
}
