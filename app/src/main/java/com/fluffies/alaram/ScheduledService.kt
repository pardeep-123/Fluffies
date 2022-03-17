package com.fluffies.alaram

import android.content.Context
import android.content.Intent
import android.util.Log

import androidx.core.app.JobIntentService


class ScheduledService : JobIntentService() {
    public override fun onHandleWork(i: Intent) {
        Log.d(javaClass.simpleName, "I ran!")
    }

    companion object {
        private const val UNIQUE_JOB_ID = 1337
        fun enqueueWork(ctxt: Context?) {
            enqueueWork(
                ctxt!!, ScheduledService::class.java, UNIQUE_JOB_ID,
                Intent(ctxt, ScheduledService::class.java)
            )
        }
    }
}