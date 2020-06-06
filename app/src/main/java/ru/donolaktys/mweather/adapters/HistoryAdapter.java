package ru.donolaktys.mweather.adapters;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.data.SearchHistory;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    private OnMenuItemClickListener itemMenuClickListener;
    private TextView textItemHistory;

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
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return SearchHistory.getSearchHistory().size();
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.itemMenuClickListener = onMenuItemClickListener;
    }

    public interface OnMenuItemClickListener {
        void onItemAddClick(String city);

        void onItemClearClick();
    }

    class HistoryHolder extends RecyclerView.ViewHolder {

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            textItemHistory = itemView.findViewById(R.id.textItemHistory);

            textItemHistory.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemMenuClickListener != null) {
                        showPopupMenu(textItemHistory);
                        return true;
                    }
                    return false;
                }
            });
        }

        public void bind(int position) {
            textItemHistory.setText(SearchHistory.getSearchHistory().get(position));
        }

        private void showPopupMenu(View view) {
            // Покажем меню на элементе
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.history_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                // обработка выбора пункта меню
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // делегируем обработку слушателю
                    switch (item.getItemId()) {
                        case R.id.menu_add:
                            itemMenuClickListener.onItemAddClick(textItemHistory.getText().toString());
                            return true;
                        case R.id.menu_clear:
                            itemMenuClickListener.onItemClearClick();
                            return true;
                    }
                    return false;
                }
            });
            popup.show();
        }
    }
}
