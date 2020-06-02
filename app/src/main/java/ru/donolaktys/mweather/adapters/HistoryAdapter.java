package ru.donolaktys.mweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.data.SearchHistory;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.item_history;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        HistoryAdapter.HistoryHolder viewHolder = new HistoryAdapter.HistoryHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.build(position);
    }

    @Override
    public int getItemCount() {
        return SearchHistory.getSearchHistory().size();
    }

    class HistoryHolder extends RecyclerView.ViewHolder {

        private TextView textItemHistory;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            textItemHistory = itemView.findViewById(R.id.textItemHistory);
        }

        public void build (int position) {
            textItemHistory.setText(SearchHistory.getSearchHistory().get(position));
        }
    }
}
