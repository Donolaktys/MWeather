package ru.donolaktys.mweather.ui.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.function.BiConsumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.donolaktys.mweather.BuildConfig;
import ru.donolaktys.mweather.MWeather;
import ru.donolaktys.mweather.AlertSender;
import ru.donolaktys.mweather.OpenWeatherImage;
import ru.donolaktys.mweather.data.SearchHistory;
import ru.donolaktys.mweather.data.Weather;
import ru.donolaktys.mweather.interfaces.Constants;
import ru.donolaktys.mweather.R;
import ru.donolaktys.mweather.data.WeatherRequest;
import ru.donolaktys.mweather.interfaces.IRequestWeather;
import ru.donolaktys.mweather.ui.home.day_view.OneDayFragment;
import ru.donolaktys.mweather.ui.home.day_view.ThreeDaysFragment;
import ru.donolaktys.mweather.ui.home.day_view.WeekFragment;

import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment implements Constants {
    private TextInputLayout localityChoiceLayout;
    private TextInputEditText localityChoice;
    private TextView temperature;
    private TextView measure;
    private TextView dayHighMeasure;
    private TextView dayLowMeasure;

    private OneDayFragment oneDayFragment;
    private ThreeDaysFragment threeDaysFragment;
    private WeekFragment weekFragment;
    private FragmentManager daysFragmentManager;

    private Button oneDayBtn;
    private Button threeDaysBtn;
    private Button weekBtn;
    private AppCompatImageView mainConditions;

    private String link;

    private IRequestWeather iRequestWeather;
    private Retrofit retrofit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        init(root);
        initRetrofit();
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
                            requestRetrofit(v.getContext(), Objects.requireNonNull(localityChoice.getText()).toString());
                            hideKeyboardFrom(requireActivity(), v);
                            SearchHistory.getSearchHistory().add(Objects.requireNonNull(localityChoice.getText()).toString());
                            break;
                    }
                    return true;
                }
                return false;
            }
        });
        return root;
    }

    private void requestRetrofit(Context context, String city) {
        // metric указано явно т.к еще не реализованы настройки. Будет браться из настроек.

        iRequestWeather.loadWeather(city, "metric", BuildConfig.WEATHER_API_KEY)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        if (response.body() !=null && response.isSuccessful()) {
                            float temp = response.body().getMain().getTemp();
                            Weather[] weather = response.body().getWeather();
                            temperature.setText(String.format(Locale.getDefault(), "%d", (int) temp));
                            Picasso.get()
                                    .load(buildImageUrl(weather[0].getIcon()))
                                    .into(mainConditions);
                        }
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            try {
                                JSONObject jsonError = new JSONObject(response.errorBody().string());
                                String errorID = jsonError.getString("cod");
                                String error = jsonError.getString("message");
                                localityChoice.getText().clear();
                                new AlertSender(context, error, errorID).send();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        localityChoice.getText().clear();
                        new AlertSender(context, (String) getText(R.string.internet_error)).send();
                    }
                });
    }

    private String buildImageUrl(String image) {
        return new OpenWeatherImage(image).build();
    }

    private void initRetrofit() {
        retrofit = MWeather.getRetrofitInstance();
        iRequestWeather = retrofit.create(IRequestWeather.class);
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void init(View view) {
        mainConditions = view.findViewById(R.id.mainConditions);
        localityChoiceLayout = view.findViewById(R.id.localityChoiceLayout);
        localityChoice = localityChoiceLayout.findViewById(R.id.localityChoice);
        localityChoiceLayout.setHint(getString(R.string.hintLocation));
        temperature = view.findViewById(R.id.temperature);
        measure = view.findViewById(R.id.measure);
        oneDayBtn = view.findViewById(R.id.oneDayBtn);
        threeDaysBtn = view.findViewById(R.id.threeDaysBtn);
        weekBtn = view.findViewById(R.id.weekBtn);
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
}
