package sunxl8.easyweather.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.RemoteViews;

import sunxl8.android_lib.utils.SizeUtils;
import sunxl8.easyweather.R;

/**
 * Created by sunxl8 on 2017/1/4.
 */

public class WeatherWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_weather);

        views.setImageViewBitmap(R.id.iv_time, getTime(context));

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

    private static Bitmap getTime(Context context) {
        Bitmap mBitmap = Bitmap.createBitmap(SizeUtils.dp2px(context, 220), SizeUtils.dp2px(context, 140), Bitmap.Config.ARGB_4444);
        Canvas mCanvas = new Canvas(mBitmap);
        Paint paint = new Paint();
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Goodbye.ttf");
        paint.setAntiAlias(true);
        paint.setAlpha(10);
        paint.setSubpixelText(true);
        paint.setTypeface(tf);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(SizeUtils.dp2px(context, 80));
        paint.setTextAlign(Paint.Align.CENTER);
        mCanvas.drawText("18:30", SizeUtils.dp2px(context, 110), SizeUtils.dp2px(context, 100), paint);
        return mBitmap;
    }
}
