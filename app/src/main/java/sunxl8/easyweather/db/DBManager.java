package sunxl8.easyweather.db;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxl8 on 2017/1/4.
 */

public class DBManager {

    public static List<WeatherEntity> queryWeatherByDateAndCityCode(String date, String code) {
        return DataSupport
                .where("date = ? and cityId = ?", date, code)
                .find(WeatherEntity.class);
    }

    public static List<CityEntity> queryCity() {
        return DataSupport
                .findAll(CityEntity.class);
    }

    public static void deleteCityById(String cityId) {
        DataSupport
                .deleteAll(WeatherEntity.class, "cityId = ?", cityId);
        DataSupport
                .deleteAll(CityEntity.class, "code = ?", cityId);
    }

    public static long getEntityIdByCityId(String cityId) {
        List<WeatherEntity> list = DataSupport
                .where("cityId = ?", cityId)
                .find(WeatherEntity.class);
        return list.size() > 0 ? list.get(0).getId() : 0;
    }
}
