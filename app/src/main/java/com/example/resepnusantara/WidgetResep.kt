package com.aditya.resepnusantara.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.aditya.resepnusantara.MainActivity
import com.aditya.resepnusantara.R

class WidgetResep : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        for (id in ids) {
            val views = RemoteViews(context.packageName, R.layout.widget_resep)

            // Ubah teksnya jika perlu
            views.setTextViewText(R.id.widget_nama, "Sate Ayam Madura")

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )

            views.setOnClickPendingIntent(R.id.widget_nama, pendingIntent)

            manager.updateAppWidget(id, views)
        }
    }

    companion object {
        fun updateAll(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(ComponentName(context, WidgetResep::class.java))
            val provider = WidgetResep()
            provider.onUpdate(context, manager, ids)
        }
    }
}
