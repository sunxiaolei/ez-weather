package sunxl8.easyweather.network;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import sunxl8.android_lib.network.NetworkManager;
import sunxl8.android_lib.network.SchedulersCompat;
import sunxl8.easyweather.Constant;
import sunxl8.easyweather.entity.SearchResponseEntity;
import sunxl8.easyweather.entity.WeatherNowResponseEntity;
import sunxl8.easyweather.entity.WeatherResponseEntity;

/**
 * Created by sunxl8 on 2017/1/3.
 */

public class WeatherRequest {

    public static Observable<SearchResponseEntity> doSearchCity(String city) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Constant.HEWEATHER_KEY);
        map.put("city", city);
        return NetworkManager.getCommonClient(Constant.BASE_URL)
                .create(WeatherSeaviceApi.class)
                .doSearchCity(map)
                .compose(SchedulersCompat.applyIoSchedulers());
    }

    public static Observable<WeatherNowResponseEntity> doGetWeatherNow(String city) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Constant.HEWEATHER_KEY);
        map.put("city", city);
        return NetworkManager.getCommonClient(Constant.BASE_URL)
                .create(WeatherSeaviceApi.class)
                .doGetWeatherNow(map)
                .compose(SchedulersCompat.applyIoSchedulers());
    }

    public static Observable<WeatherResponseEntity> doGetWeather(String city) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Constant.HEWEATHER_KEY);
        map.put("city", city);
        return NetworkManager.getCommonClient(Constant.BASE_URL)
                .create(WeatherSeaviceApi.class)
                .doGetWeather(map)
                .compose(SchedulersCompat.applyIoSchedulers());
    }

}
