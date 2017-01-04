package sunxl8.easyweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by sunxl8 on 2017/1/4.
 */

public class WeatherEntity extends DataSupport{

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
}
