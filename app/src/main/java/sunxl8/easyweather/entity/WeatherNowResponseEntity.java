package sunxl8.easyweather.entity;

import java.util.List;

/**
 * Created by sunxl8 on 2017/1/3.
 */

public class WeatherNowResponseEntity {

    private List<WeatherResponseEntity.HeWeather5Bean> HeWeather5;

    public List<WeatherResponseEntity.HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<WeatherResponseEntity.HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

}
