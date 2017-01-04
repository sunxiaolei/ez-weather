package sunxl8.easyweather.entity;

import java.util.List;

/**
 * Created by sunxl8 on 2017/1/3.
 */

public class CityWeatherResponseEntity {

    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","prov":"北京","update":{"loc":"2016-08-31 11:52","utc":"2016-08-31 03:52"}}
         * now : {"cond":{"code":"104","txt":"阴"},"fl":"11","hum":"31","pcpn":"0","pres":"1025","tmp":"13","vis":"10","wind":{"deg":"40","dir":"东北风","sc":"4-5","spd":"24"}}
         * status : ok
         */

        private WeatherBasic basic;
        private WeatherNow now;
        private String status;

        public WeatherBasic getBasic() {
            return basic;
        }

        public void setBasic(WeatherBasic basic) {
            this.basic = basic;
        }

        public WeatherNow getNow() {
            return now;
        }

        public void setNow(WeatherNow now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

}
