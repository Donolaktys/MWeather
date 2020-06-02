package ru.donolaktys.mweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.donolaktys.mweather.OpenWeatherImage;
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
        private TextView textTempFavorite;
        private AppCompatImageView imageConditionsFavorite;


        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            textCityFavorite = itemView.findViewById(R.id.textCityFavorite);
            imageConditionsFavorite = itemView.findViewById(R.id.imageConditionsFavorite);
            textTempFavorite = itemView.findViewById(R.id.textTempFavorite);
        }

        public void build(int position) {
            String[] list = FavoriteCity.getFavoriteCity().get(position);
            textCityFavorite.setText(list[0]);
            Picasso.get()
                    .load(new OpenWeatherImage(list[1]).build())
                    .into(imageConditionsFavorite);
            textTempFavorite.setText(list[2]);
        }
    }
}
