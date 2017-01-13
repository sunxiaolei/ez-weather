package sunxl8.easyweather.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.RemoteViews;

import sunxl8.android_lib.utils.SizeUtils;
import sunxl8.easyweather.R;
import sunxl8.easyweather.db.WeatherEntity;

/**
 * Created by sunxl8 on 2017/1/4.
 */

public class WeatherWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_weather);

        views.setImageViewBitmap(R.id.iv_time, getTime(context, "N/A", "N/A", "N/A", "N/A", "N/A"));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String weather = intent.getStringExtra("weather");
        String city = intent.getStringExtra("city");
        String tem = intent.getStringExtra("tem");
        String pm25 = intent.getStringExtra("pm25");
        String qlty = intent.getStringExtra("qlty");
        if (!TextUtils.isEmpty(weather)
                && !TextUtils.isEmpty(city)
                && !TextUtils.isEmpty(tem)
                && !TextUtils.isEmpty(pm25)
                && !TextUtils.isEmpty(qlty)) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_weather);
            remoteViews.setImageViewBitmap(R.id.iv_time, getTime(context, city, weather, tem, qlty, pm25));
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            //区分：RemoteViews代表App Widget中的所有空间，而ComponentName代表整个App Widget对象
            ComponentName componentName = new ComponentName(context, WeatherWidget.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    private static Bitmap getTime(Context context, String city, String weather, String tem, String qlty, String pm25) {
        Bitmap mBitmap = Bitmap.createBitmap(SizeUtils.dp2px(context, 380), SizeUtils.dp2px(context, 200), Bitmap.Config.ARGB_4444);
        Canvas mCanvas = new Canvas(mBitmap);
//        mCanvas.drawColor(Color.BLACK);
        Typeface tfC = Typeface.createFromAsset(context.getAssets(), "fonts/byxs.ttf");
        Typeface tfE = Typeface.createFromAsset(context.getAssets(), "fonts/Enoksen.ttf");

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(tfC);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(SizeUtils.dp2px(context, 120));
        paint.setTextAlign(Paint.Align.CENTER);
        mCanvas.drawText(weather, SizeUtils.dp2px(context, 190), SizeUtils.dp2px(context, 160), paint);

        Paint paintCity = new Paint();
        paintCity.setAntiAlias(true);
        paintCity.setSubpixelText(true);
        paintCity.setTypeface(tfC);
        paintCity.setStyle(Paint.Style.FILL);
        paintCity.setColor(Color.WHITE);
        paintCity.setTextSize(SizeUtils.dp2px(context, 30));
        paintCity.setTextAlign(Paint.Align.LEFT);
        mCanvas.drawText(city, SizeUtils.dp2px(context, 0), SizeUtils.dp2px(context, 45), paintCity);

        Paint paintTem = new Paint();
        paintTem.setAntiAlias(true);
        paintTem.setSubpixelText(true);
        paintTem.setTypeface(tfC);
        paintTem.setStyle(Paint.Style.FILL);
        paintTem.setColor(Color.WHITE);
        paintTem.setTextSize(SizeUtils.dp2px(context, 50));
        paintTem.setTextAlign(Paint.Align.RIGHT);
        mCanvas.drawText(tem, SizeUtils.dp2px(context, 380), SizeUtils.dp2px(context, 70), paintTem);

        Paint paintQlty = new Paint();
        paintQlty.setAntiAlias(true);
        paintQlty.setSubpixelText(true);
        paintQlty.setTypeface(tfC);
        paintQlty.setStyle(Paint.Style.FILL);
        paintQlty.setColor(Color.WHITE);
        paintQlty.setTextSize(SizeUtils.dp2px(context, 50));
        paintQlty.setTextAlign(Paint.Align.RIGHT);
        mCanvas.drawText(qlty, SizeUtils.dp2px(context, 380), SizeUtils.dp2px(context, 195), paintQlty);

//        Paint paintPM = new Paint();
//        paintPM.setAntiAlias(true);
//        paintPM.setSubpixelText(true);
//        paintPM.setTypeface(tfE);
//        paintPM.setStyle(Paint.Style.FILL);
//        paintPM.setColor(Color.WHITE);
//        paintPM.setTextSize(SizeUtils.dp2px(context, 20));
//        paintPM.setTextAlign(Paint.Align.LEFT);
//        mCanvas.drawText(pm25, SizeUtils.dp2px(context, 0), SizeUtils.dp2px(context, 195), paintPM);

        return mBitmap;
    }

}
