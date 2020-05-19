package ru.donolaktys.mweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.donolaktys.mweather.R;

public class DayViewAdapter extends RecyclerView.Adapter<DayViewAdapter.DayViewHolder> {
    private int numberItems;

    public DayViewAdapter(int numberOfItems) {
        numberItems = numberOfItems;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutIdForListItem = R.layout.item_view_day;

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        DayViewHolder viewHolder = new DayViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int i) {
        dayViewHolder.build(i);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class DayViewHolder extends RecyclerView.ViewHolder {

        private TextView dataDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM");
        Calendar calendar = new GregorianCalendar();

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            init();
        }

        void build(int listIndex) {
            calendar.roll(Calendar.DAY_OF_MONTH, listIndex);
            String dayDate = simpleDateFormat.format(calendar.getTime());
            dataDate.setText(dayDate);
        }

        void init() {
            dataDate = itemView.findViewById(R.id.dataDate);
        }
    }
}
