package ru.donolaktys.mweather.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.donolaktys.mweather.R;

public class SettingsFragment extends Fragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

//        SettingsInterface settings = SettingsHandler.getSettings();
//        SwitchCompat switchDarkTheme = root.findViewById(R.id.nightModeSwitch);
//        switchDarkTheme.setChecked(isDarkTheme());
//        switchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                setDarkTheme(isChecked);
//                recreate();
//            }
//        });

        return root;
    }
}
