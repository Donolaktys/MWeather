package ru.donolaktys.mweather;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.donolaktys.mweather.data.WeatherRequest;

public class RequestBuilder implements Constants {
    private WeatherRequest weatherRequest;

    public RequestBuilder(WeatherRequest weatherRequest, String uri) {
        this.weatherRequest = weatherRequest;
        build(uri);
    }

    public void build(String uri) {
        try{
            final URL url = new URL(uri);
            Thread t1 = new Thread(new Runnable() {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);
                        Gson gson = new Gson();
                        weatherRequest = gson.fromJson(result, WeatherRequest.class);
                    }catch(Exception e){
                        Log.e(TAG, "Fail Connection", e);
                        e.printStackTrace();
                    } finally {
                        urlConnection.disconnect();
                    }
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                private String getLines(BufferedReader in) {
                    return in.lines().collect(Collectors.joining("\n"));
                }

            });
            t1.start();
            t1.join();
        }catch(Exception e){
            Log.e(TAG, "Fail URL", e);
            e.printStackTrace();
        }
    }

    public WeatherRequest getWeatherRequest() {
        return weatherRequest;
    }
}
