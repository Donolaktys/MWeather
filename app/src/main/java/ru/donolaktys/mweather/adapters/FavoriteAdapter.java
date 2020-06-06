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
import ru.donolaktys.mweather.data.FavoriteCity;
import ru.donolaktys.mweather.interfaces.AdapterChangeable;
import ru.donolaktys.mweather.interfaces.IFavoriteRepository;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>
        implements AdapterChangeable {

    private IFavoriteRepository repository;
    private OnMenuItemClickListener itemMenuClickListener;

    public FavoriteAdapter(IFavoriteRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.item_favorite;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        FavoriteAdapter.FavoriteHolder viewHolder = new FavoriteAdapter.FavoriteHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        holder.bind(repository.getFavoriteById(position));
    }

    @Override
    public int getItemCount() {
        return repository.getCount();
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener){
        this.itemMenuClickListener = onMenuItemClickListener;
    }

    public interface OnMenuItemClickListener {
        void onItemDeleteClick(FavoriteCity favoriteCity);
    }

    @Override
    public void notifyDataChange() {
        notifyDataSetChanged();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder {

        private TextView textCityFavorite;
        private TextView textTempFavorite;
        private AppCompatImageView imageConditionsFavorite;
        private FavoriteCity favoriteCity;


        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            textCityFavorite = itemView.findViewById(R.id.textCityFavorite);
            imageConditionsFavorite = itemView.findViewById(R.id.imageConditionsFavorite);
            textTempFavorite = itemView.findViewById(R.id.textTempFavorite);

            textCityFavorite.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemMenuClickListener != null) {
                        showPopupMenu(textCityFavorite);
                        return true;
                    }
                    return false;
                }
            });
        }

        public void bind(FavoriteCity favoriteCity) {
            this.favoriteCity = favoriteCity;
            textCityFavorite.setText(favoriteCity.getCity());
            Picasso.get()
                    .load(new OpenWeatherImage(favoriteCity.getImage()).build())
                    .into(imageConditionsFavorite);
            textTempFavorite.setText(favoriteCity.getTemperature());
        }

        private void showPopupMenu(View view) {
            // Покажем меню на элементе
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.context_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                // обработка выбора пункта меню
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // делегируем обработку слушателю
                    switch (item.getItemId()) {
                        case R.id.menu_delete:
                            itemMenuClickListener.onItemDeleteClick(favoriteCity);
                            return true;
                    }
                    return false;
                }
            });
            popup.show();
        }
    }
}
