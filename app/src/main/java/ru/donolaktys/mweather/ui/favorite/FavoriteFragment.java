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
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        init();
        addFragment(savedInstanceState, R.id.content_favorite, favoriteListFragment);
        addFragment(savedInstanceState, R.id.content_history, historyListFragment);
        return root;
    }

    private void init() {
        fragmentManager = getParentFragmentManager();
        favoriteListFragment = new FavoriteListFragment();
        historyListFragment = new HistoryListFragment();
    }

    private void addFragment(Bundle savedInstanceState, int id, Fragment fragment) {
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.add(id, fragment);
            fragmentTransaction.commit();
        }
    }
}
