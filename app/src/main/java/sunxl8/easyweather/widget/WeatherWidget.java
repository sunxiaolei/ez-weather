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

        views.setImageViewBitmap(R.id.iv_time, getTime(context, "N/A", "N/A"));

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
        if (weather != null && city != null) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_weather);
            remoteViews.setImageViewBitmap(R.id.iv_time, getTime(context, city, weather));
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            //区分：RemoteViews代表App Widget中的所有空间，而ComponentName代表整个App Widget对象
            ComponentName componentName = new ComponentName(context, WeatherWidget.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    private static Bitmap getTime(Context context, String city, String weather) {
        Bitmap mBitmap = Bitmap.createBitmap(SizeUtils.dp2px(context, 380), SizeUtils.dp2px(context, 200), Bitmap.Config.ARGB_4444);
        Canvas mCanvas = new Canvas(mBitmap);
//        mCanvas.drawColor(Color.BLACK);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/byxs.ttf");

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(tf);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(SizeUtils.dp2px(context, 120));
        paint.setTextAlign(Paint.Align.CENTER);
        mCanvas.drawText(weather, SizeUtils.dp2px(context, 190), SizeUtils.dp2px(context, 160), paint);

        Paint paintCity = new Paint();
        paintCity.setAntiAlias(true);
        paintCity.setSubpixelText(true);
        paintCity.setTypeface(tf);
        paintCity.setStyle(Paint.Style.FILL);
        paintCity.setColor(Color.WHITE);
        paintCity.setTextSize(SizeUtils.dp2px(context, 20));
        paintCity.setTextAlign(Paint.Align.LEFT);
        mCanvas.drawText(city, SizeUtils.dp2px(context, 0), SizeUtils.dp2px(context, 25), paintCity);
        return mBitmap;
    }
}
