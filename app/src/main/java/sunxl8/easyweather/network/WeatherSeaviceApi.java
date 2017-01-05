package sunxl8.easyweather.network;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import sunxl8.easyweather.entity.SearchResponseEntity;
import sunxl8.easyweather.entity.WeatherNowResponseEntity;
import sunxl8.easyweather.entity.WeatherResponseEntity;

/**
 * Created by sunxl8 on 2017/1/3.
 */

public interface WeatherSeaviceApi {

    ///////////////////////////////////////////////////////////////////////////
    // 城市查询
    ///////////////////////////////////////////////////////////////////////////
    @GET("search")
    Observable<SearchResponseEntity> doSearchCity(@QueryMap Map<String,String> map);

    ///////////////////////////////////////////////////////////////////////////
    // 实时天气
    ///////////////////////////////////////////////////////////////////////////
    @GET("now")
    Observable<WeatherNowResponseEntity> doGetWeatherNow(@QueryMap Map<String,String> map);

    ///////////////////////////////////////////////////////////////////////////
    // 天气集合
    ///////////////////////////////////////////////////////////////////////////
    @GET("weather")
    Observable<WeatherResponseEntity> doGetWeather(@QueryMap Map<String,String> map);
}
