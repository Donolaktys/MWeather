package ru.donolaktys.mweather.adapters;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.donolaktys.mweather.OpenWeatherImage;
import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.data.History;
import ru.donolaktys.mweather.interfaces.AdapterChangeable;
import ru.donolaktys.mweather.interfaces.IHistoryRepository;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder>
        implements AdapterChangeable {

    private IHistoryRepository repository;
    private OnMenuItemClickListener itemMenuClickListener;

    public HistoryAdapter(IHistoryRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.item_favorite;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        HistoryHolder viewHolder = new HistoryHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.bind(repository.get(position));
    }

    @Override
    public int getItemCount() {
        return repository.getCount();
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener){
        this.itemMenuClickListener = onMenuItemClickListener;
    }

    public interface OnMenuItemClickListener {
        void onItemDeleteClick(History history);
        void onItemClearClick();
    }

    @Override
    public void notifyDataChange() {
        notifyDataSetChanged();
    }

    class HistoryHolder extends RecyclerView.ViewHolder {

        private TextView textCityHistory;
        private TextView textTempHistory;
        private AppCompatImageView imageConditionsFavorite;
        private History history;


        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            textCityHistory = itemView.findViewById(R.id.textCityHistory);
            imageConditionsFavorite = itemView.findViewById(R.id.imageConditionsHistory);
            textTempHistory = itemView.findViewById(R.id.textTempHistory);

            textCityHistory.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemMenuClickListener != null) {
                        showPopupMenu(textCityHistory);
                        return true;
                    }
                    return false;
                }
            });
        }

        public void bind(History history) {
            this.history = history;
            textCityHistory.setText(history.getCity());
            Picasso.get()
                    .load(new OpenWeatherImage(history.getImage()).build())
                    .into(imageConditionsFavorite);
            textTempHistory.setText(history.getTemperature());
        }

        private void showPopupMenu(View view) {

            PopupMenu popup = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.context_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.menu_delete:
                            itemMenuClickListener.onItemDeleteClick(history);
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
