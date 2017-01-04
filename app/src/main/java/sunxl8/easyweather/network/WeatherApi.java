package sunxl8.easyweather.network;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import sunxl8.easyweather.entity.CitySearchResponseEntity;
import sunxl8.easyweather.entity.CityWeatherResponseEntity;

/**
 * Created by sunxl8 on 2017/1/3.
 */

public interface WeatherApi {

    ///////////////////////////////////////////////////////////////////////////
    // 城市查询
    ///////////////////////////////////////////////////////////////////////////
    @GET("search")
    Observable<CitySearchResponseEntity> doSearchCity(@QueryMap Map<String,String> map);

    ///////////////////////////////////////////////////////////////////////////
    // 实时天气
    ///////////////////////////////////////////////////////////////////////////
    @GET("now")
    Observable<CityWeatherResponseEntity> doGetWeatherNow(@QueryMap Map<String,String> map);
}
