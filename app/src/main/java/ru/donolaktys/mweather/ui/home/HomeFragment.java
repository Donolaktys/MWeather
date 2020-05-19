package ru.donolaktys.mweather.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.ui.home.day_view.OneDayFragment;
import ru.donolaktys.mweather.ui.home.day_view.ThreeDaysFragment;
import ru.donolaktys.mweather.ui.home.day_view.WeekFragment;

public class HomeFragment extends Fragment {
    private TextView localityChoice;
    private TextView temperature;
    private TextView measure;
    private TextView infoLink;
    private TextView dayHighMeasure;
    private TextView dayLowMeasure;

    private OneDayFragment oneDayFragment;
    private ThreeDaysFragment threeDaysFragment;
    private WeekFragment weekFragment;
    private FragmentManager daysFragmentManager;

    private Button oneDayBtn;
    private Button threeDaysBtn;
    private Button weekBtn;

    private String link;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        addFirstFragment(savedInstanceState);

        return root;
    }

    private void init() {
        localityChoice = getActivity().findViewById(R.id.localityChoice);
        temperature = getActivity().findViewById(R.id.temperature);
        measure = getActivity().findViewById(R.id.measure);
        oneDayBtn = getActivity().findViewById(R.id.oneDayBtn);
        threeDaysBtn = getActivity().findViewById(R.id.threeDaysBtn);
        weekBtn = getActivity().findViewById(R.id.weekBtn);
        infoLink = getActivity().findViewById(R.id.infoLink);
        dayHighMeasure = getActivity().findViewById(R.id.dayHighMeasure);
        dayLowMeasure = getActivity().findViewById(R.id.dayLowMeasure);
        link = getActivity().getString(R.string.link);
        daysFragmentManager = getParentFragmentManager();
        oneDayFragment = new OneDayFragment();
        threeDaysFragment = new ThreeDaysFragment();
        weekFragment = new WeekFragment();
//        measure.setText(getMeasure());
//        dayHighMeasure.setText(getMeasure());
//        dayLowMeasure.setText(getMeasure());
    }

    private void addFirstFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = daysFragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.home_fragment, oneDayFragment);
            fragmentTransaction.commit();
        }
    }
}
