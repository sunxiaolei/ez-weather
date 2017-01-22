package sunxl8.easyweather.db;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxl8 on 2017/1/4.
 */

public class DBManager {

    public static List<WeatherEntity> queryWeatherByDate(String date) {
        return DataSupport
                .where("date = ?", date)
                .find(WeatherEntity.class);
    }

    public static List<CityEntity> queryCity() {
        return DataSupport
                .findAll(CityEntity.class);
    }
}
