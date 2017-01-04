package sunxl8.easyweather.entity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/1/4.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class WeatherAlarms {


    /**
     * level : 蓝色
     * stat : 预警中
     * title : 山东省青岛市气象台发布大风蓝色预警
     * txt : 青岛市气象台2016年08月29日15时24分继续发布大风蓝色预警信号：预计今天下午到明天，我市北风风力海上6到7级阵风9级，陆地4到5阵风7级，请注意防范。
     * type : 大风
     */

    private String level;
    private String stat;
    private String title;
    private String txt;
    private String type;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
