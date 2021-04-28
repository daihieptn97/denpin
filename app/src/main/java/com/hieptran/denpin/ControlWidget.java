package com.hieptran.denpin;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class ControlWidget extends AppWidgetProvider {
    private boolean isTurnOnFlashlight = false;

    private static final String ACTION_CLICK_CONTROL = "actionClickControl";
    private FlashlightProvider flashlightProvider;

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {



        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.control_widget);

        views.setOnClickPendingIntent(R.id.btnWidgetControl, getPendingSelfIntent(context, ACTION_CLICK_CONTROL));

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
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.e(getClass().getSimpleName(), "onReceive:" + action);

        flashlightProvider = new FlashlightProvider(context);

        if (action == null) {
            Log.e(getClass().getSimpleName(), "action null");
            return;
        }


        switch (action) {
            case ACTION_CLICK_CONTROL:
                if (isTurnOnFlashlight) {
                    flashlightProvider.turnFlashlightOff();
                    isTurnOnFlashlight = false;
                } else {
                    flashlightProvider.turnFlashlightOn();
                    isTurnOnFlashlight = true;
                }
                break;
        }
    }

    private PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}