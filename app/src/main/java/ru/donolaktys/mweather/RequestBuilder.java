package ru.donolaktys.mweather;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.donolaktys.mweather.data.WeatherRequest;
import ru.donolaktys.mweather.interfaces.Constants;

public class RequestBuilder implements Constants {
    private final RequestListener requestListener;
    private WeatherRequest weatherRequest;

    public RequestBuilder(RequestListener requestListener, WeatherRequest weatherRequest, String uri) {
        this.requestListener = requestListener;
        this.weatherRequest = weatherRequest;
        build(uri);
    }

    public void build(String uri) {
        try{
            final Handler handler = new Handler(Looper.myLooper());
            final URL url = new URL(uri);
            new Thread(new Runnable() {

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
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                requestListener.onFinish(getTemperature(weatherRequest));
                            }
                        });
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
            }).start();
        }catch(Exception e){
            Log.e(TAG, "Fail URL", e);
            e.printStackTrace();
        }
    }

    private String getTemperature(WeatherRequest weatherRequest) {
        String temperature;
        try {
            temperature = (String.format("%d", (int) weatherRequest.getMain().getTemp()));
        } catch (NullPointerException e) {
            temperature = "";
        }
        return temperature;
    }

    public WeatherRequest getWeatherRequest() {
        return weatherRequest;
    }

    public interface RequestListener{
        void onFinish(String param);
    }
}
