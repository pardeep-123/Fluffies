package com.puppypedia.alaram

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock


class PollReciver : BroadcastReceiver() {
    override fun onReceive(ctxt: Context, i: Intent) {
//        if (i.action == null) {
//            ScheduledService.enqueueWork(ctxt)
//        } else {
//            scheduleAlarms(ctxt)
//        }
    }

    companion object {
        private const val PERIOD = 900000 // 15 minutes
        private const val INITIAL_DELAY = 5000 // 5 seconds
        fun scheduleAlarms(ctxt: Context) {
            val mgr = ctxt.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val i = Intent(ctxt, PollReciver::class.java)
            val pi = PendingIntent.getBroadcast(ctxt, 0, i, 0)
            mgr.set(AlarmManager.RTC_WAKEUP, 1642772210, pi)
//            mgr.setRepeating(
//                AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                1642767721,
//                PERIOD.toLong(), pi
//            )
        }
    }
}