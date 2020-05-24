package ru.donolaktys.mweather.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import ru.donolaktys.mweather.BuildConfig;
import ru.donolaktys.mweather.Constants;
import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.RequestBuilder;
import ru.donolaktys.mweather.UriBuilder;
import ru.donolaktys.mweather.data.WeatherRequest;
import ru.donolaktys.mweather.ui.home.day_view.OneDayFragment;
import ru.donolaktys.mweather.ui.home.day_view.ThreeDaysFragment;
import ru.donolaktys.mweather.ui.home.day_view.WeekFragment;

public class HomeFragment extends Fragment implements Constants {
    private TextInputLayout localityChoiceLayout;
    private TextInputEditText localityChoice;
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

        init(root);
        addFirstFragment(savedInstanceState);

        oneDayBtn.setOnClickListener(v -> {
            replaceFragment(oneDayFragment);
        });

        threeDaysBtn.setOnClickListener(v -> {
            replaceFragment(threeDaysFragment);
        });

        weekBtn.setOnClickListener(v -> {
            replaceFragment(weekFragment);
        });

        localityChoice.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.ACTION_DOWN == event.getAction()) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            UriBuilder uriBuilder = new UriBuilder(Objects.requireNonNull(localityChoice.getText()).toString());
                            initBuilder(uriBuilder.getRequestUri());
//                            putSearchHistory(city, temperature.getText().toString());
                            hideKeyboardFrom(requireActivity(), v);
                            break;
                    }
                    return true;
                }
                return false;
            }
        });

        return root;
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void init(View view) {
        localityChoiceLayout = view.findViewById(R.id.localityChoiceLayout);
        localityChoice = localityChoiceLayout.findViewById(R.id.localityChoice);
        localityChoiceLayout.setHint(getString(R.string.hintLocation));
        temperature = view.findViewById(R.id.temperature);
        measure = view.findViewById(R.id.measure);
        oneDayBtn = view.findViewById(R.id.oneDayBtn);
        threeDaysBtn = view.findViewById(R.id.threeDaysBtn);
        weekBtn = view.findViewById(R.id.weekBtn);
        infoLink = view.findViewById(R.id.infoLink);
        dayHighMeasure = view.findViewById(R.id.dayHighMeasure);
        dayLowMeasure = view.findViewById(R.id.dayLowMeasure);
        link = getActivity().getString(R.string.link);
        daysFragmentManager = getParentFragmentManager();
        oneDayFragment = new OneDayFragment();
        threeDaysFragment = new ThreeDaysFragment();
        weekFragment = new WeekFragment();
    }

    private void addFirstFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = daysFragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.home_fragment, oneDayFragment);
            fragmentTransaction.commit();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void initBuilder(String uri) {
        final RequestBuilder requestBuilder = new RequestBuilder(new WeatherRequest(), uri);
        displayWeather(requestBuilder.getWeatherRequest());
    }

    private void displayWeather(WeatherRequest weatherRequest) {
        try {
            temperature.setText(String.format("%d", (int) weatherRequest.getMain().getTemp()));
        } catch (NullPointerException e) {
            localityChoice.setText("");
            Toast toast = Toast.makeText(getContext(),
                    "Не верный запрос!!!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
