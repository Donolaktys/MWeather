package ru.donolaktys.mweather.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import ru.donolaktys.mweather.HistoryLogic;
import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.adapters.HistoryAdapter;
import ru.donolaktys.mweather.data.History;
import ru.donolaktys.mweather.data.RoomHistoryRepository;

public class FavoriteFragment extends Fragment {
    private RoomHistoryRepository favoriteRepository;
    private HistoryLogic historyLogic;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_favorite, container, false);
        favoriteRepository = new RoomHistoryRepository();
        historyLogic = new HistoryLogic(favoriteRepository);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.fragmentViewFavorite);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        HistoryAdapter adapter = new HistoryAdapter(historyLogic.getRepository());
        historyLogic.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(root.getContext(),  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(Objects.requireNonNull(root.getContext().getDrawable(R.drawable.day_view_decorator)));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnMenuItemClickListener(new HistoryAdapter.OnMenuItemClickListener() {
            @Override
            public void onItemDeleteClick(History history) {
                historyLogic.deleteHistory(history);
            }

            @Override
            public void onItemClearClick() {
                historyLogic.clearList();
            }
        });
        recyclerView.setAdapter(adapter);

        return root;
    }
}
