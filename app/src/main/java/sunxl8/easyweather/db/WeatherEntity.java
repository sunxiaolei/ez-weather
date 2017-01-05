package sunxl8.easyweather.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by sunxl8 on 2017/1/4.
 */

public class WeatherEntity extends DataSupport implements Serializable{

    private long id;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 国家
     */
    private String cnty;
    /**
     * 城市ID
     */
    private String cityId;
    /**
     * 城市维度
     */
    private String lat;
    /**
     * 城市经度
     */
    private String lon;
    /**
     * 城市所属省份（仅限国内城市）
     */
    private String prov;
    /**
     * 日期
     */
    private String date;
    /**
     * 天气状况代码
     */
    private String code;
    /**
     * 天气状况描述
     */
    private String txt;
    /**
     * 体感温度
     */
    private String fl;
    /**
     * 相对湿度（%）
     */
    private String hum;
    /**
     * 降水量（mm）
     */
    private String pcpn;
    /**
     * 气压
     */
    private String pres;
    /**
     * 温度
     */
    private String tmp;
    /**
     * 能见度（km）
     */
    private String vis;
    /**
     * 风向（360度）
     */
    private String deg;
    /**
     * 风向
     */
    private String dir;
    /**
     * 风力
     */
    private String sc;
    /**
     * 风速（kmph）
     */
    private String spd;
    /**
     * AQI
     */
    private String aqi;
    /**
     * CO
     */
    private String co;
    /**
     * NO2
     */
    private String no2;
    /**
     * O3
     */
    private String o3;
    /**
     * PM1010
     */
    private String pm10;
    /**
     * PM2.5
     */
    private String pm25;
    /**
     * 空气质量
     */
    private String qlty;
    /**
     * SO2
     */
    private String so2;
    /**
     * 舒适度指数
     */
    private String comfBrf;
    private String comfTxt;
    /**
     * 洗车指数
     */
    private String cwBrf;
    private String cwTxt;
    /**
     * 穿衣指数
     */
    private String drsgBrf;
    private String drsgTxt;
    /**
     * 感冒指数
     */
    private String fluBrf;
    private String fluTxt;
    /**
     * 运动指数
     */
    private String sportBrf;
    private String sportTxt;
    /**
     * 旅游指数
     */
    private String travBrf;
    private String travTxt;
    /**
     * 紫外线指数
     */
    private String uvBrf;
    private String uvTxt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getComfBrf() {
        return comfBrf;
    }

    public void setComfBrf(String comfBrf) {
        this.comfBrf = comfBrf;
    }

    public String getComfTxt() {
        return comfTxt;
    }

    public void setComfTxt(String comfTxt) {
        this.comfTxt = comfTxt;
    }

    public String getCwBrf() {
        return cwBrf;
    }

    public void setCwBrf(String cwBrf) {
        this.cwBrf = cwBrf;
    }

    public String getCwTxt() {
        return cwTxt;
    }

    public void setCwTxt(String cwTxt) {
        this.cwTxt = cwTxt;
    }

    public String getDrsgBrf() {
        return drsgBrf;
    }

    public void setDrsgBrf(String drsgBrf) {
        this.drsgBrf = drsgBrf;
    }

    public String getDrsgTxt() {
        return drsgTxt;
    }

    public void setDrsgTxt(String drsgTxt) {
        this.drsgTxt = drsgTxt;
    }

    public String getFluBrf() {
        return fluBrf;
    }

    public void setFluBrf(String fluBrf) {
        this.fluBrf = fluBrf;
    }

    public String getFluTxt() {
        return fluTxt;
    }

    public void setFluTxt(String fluTxt) {
        this.fluTxt = fluTxt;
    }

    public String getSportBrf() {
        return sportBrf;
    }

    public void setSportBrf(String sportBrf) {
        this.sportBrf = sportBrf;
    }

    public String getSportTxt() {
        return sportTxt;
    }

    public void setSportTxt(String sportTxt) {
        this.sportTxt = sportTxt;
    }

    public String getTravBrf() {
        return travBrf;
    }

    public void setTravBrf(String travBrf) {
        this.travBrf = travBrf;
    }

    public String getTravTxt() {
        return travTxt;
    }

    public void setTravTxt(String travTxt) {
        this.travTxt = travTxt;
    }

    public String getUvBrf() {
        return uvBrf;
    }

    public void setUvBrf(String uvBrf) {
        this.uvBrf = uvBrf;
    }

    public String getUvTxt() {
        return uvTxt;
    }

    public void setUvTxt(String uvTxt) {
        this.uvTxt = uvTxt;
    }
}
