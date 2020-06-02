package ru.donolaktys.mweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.data.FavoriteCity;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>{

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
        holder.build(position);
    }

    @Override
    public int getItemCount() {
        return FavoriteCity.getFavoriteCity().size();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder {

        private TextView textCityFavorite;
        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            textCityFavorite = itemView.findViewById(R.id.textCityFavorite);
        }

        public void build(int position) {
            textCityFavorite.setText(FavoriteCity.getFavoriteCity().get(position).get(0));
        }
    }
}
