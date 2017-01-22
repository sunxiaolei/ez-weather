package sunxl8.easyweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by sunxl8 on 2017/1/22.
 */

public class CityEntity extends DataSupport {

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
