package ru.donolaktys.mweather.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.donolaktys.mweather.R;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        TextView view = root.getRootView().findViewById(R.id.text_about);
        view.setText(R.string.about_text);
        return root;
    }
}
