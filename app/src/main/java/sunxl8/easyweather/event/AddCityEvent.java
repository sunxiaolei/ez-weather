package sunxl8.easyweather.event;

/**
 * Created by sunxl8 on 2017/1/23.
 */

public class AddCityEvent {

    private String cityCode;
    private String cityName;

    public AddCityEvent(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }
}
