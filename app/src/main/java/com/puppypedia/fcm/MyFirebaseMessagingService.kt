package com.puppypedia.fcm


import android.annotation.SuppressLint
import android.app.Notification
import android.app.Notification.DEFAULT_ALL
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.puppypedia.R
import com.puppypedia.utils.helper.others.SharedPrefUtil
import org.json.JSONObject
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {
    var TAG = "FirebaseService"
    var title = ""
    var message = ""
    var notificationCode = ""
    var notificationType = ""
    var caseId = 1
    var orderStatus = ""
    var product_id = ""
    var otherUserId = ""
    var otherUserName = ""
    var otherUserImg = ""
    companion object {
        var i = 0
    }
    //for oreo
    var CHANNEL_ID = ""
    var CHANNEL_ONE_NAME = "Channel One"
    var notificationManager: NotificationManager? = null
    lateinit var notificationChannel: NotificationChannel
    lateinit var notification: Notification

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Uniques Message data payload: ${remoteMessage.data}")
            Log.e(TAG, "Uniques check load: 1111111111111111")
            createNotification(remoteMessage)
        }
        remoteMessage.notification?.let {
            Log.d(TAG, "Uniques Message Notification Body: ${it.body}")
//            Log.e(TAG, "Uniques check load: 222222222222222")
        }
    }
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        SharedPrefUtil.getInstance().saveFcmToken(p0)
        Log.e(TAG, "fcm token : $p0")

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun createNotification(remoteMessage: RemoteMessage) {
        Log.e(TAG, "Uniques check load: 33333333333333333")

        if (notificationManager == null)
            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val sound: Uri =
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + applicationContext.packageName + "/" + R.raw.alarm) //Here is FILE_NAME is the name of file that you want to play
        val audioAttributes: AudioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()
        CHANNEL_ID = applicationContext.packageName
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                CHANNEL_ID, CHANNEL_ONE_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(sound, audioAttributes)
            notificationChannel.vibrationPattern = LongArray(0)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.setShowBadge(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }
        try {
            if (remoteMessage.data["message"].toString().length > 15) {
                message = remoteMessage.data["message"].toString()
                    .replaceRange(15, remoteMessage.data["message"].toString().length, "....")
            } else {
                message = remoteMessage.data["message"].toString()
            }
            val jsonObject = JSONObject(remoteMessage.data["body"])
            notificationType = jsonObject.getString("type")
            if (jsonObject.getString("type") != "14") {
                product_id = jsonObject.getString("product_id")
                Log.e(TAG, "Uniques check load: 6666666666666666666")
            }
            if (jsonObject.getString("type") == "14") {
                otherUserId = jsonObject.getString("senderId")
                otherUserImg = jsonObject.getString("senderImage")
                otherUserName = jsonObject.getString("senderName")
                Log.e(TAG, "Uniques check load: 77777777777777777777")
            }
            title = getString(R.string.app_name)
        } catch (ex: Exception) {
        }

        var intent = Intent()
        Log.e("message get  innnner", notificationType)
        /*when (notificationType) {
            "1" -> {
                intent = Intent(
                    applicationContext,
                ).putExtra(
                    getString(R.string.set_product_id),
                    product_id
                ).putExtra(
                    getString(R.string.set_is_my_product),
                    true
                ).putExtra("fromNotification", "0")

            }
            "14" -> {
                Log.e(TAG, "Uniques check load: 8888888888888888888888888")

                intent =
                    Intent(applicationContext, ChatActivity::class.java)
                        .putExtra(
                            "id",
                            otherUserId
                        ).putExtra(
                            "img",
                            if (otherUserImg.contains(CommonKeys.URL))
                                otherUserImg
                            else
                                CommonKeys.Image_Url + otherUserImg
                        ).putExtra(
                            "name",
                            otherUserName
                        ).putExtra("fromNotification", "0")


            }
        }*/
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent =
            PendingIntent.getActivity(
                applicationContext,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        val icon1 = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        // val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val soundUri = Uri.parse("file:///android_asset/hopo.mp3")
        val notificationBuilder: Notification.Builder = Notification.Builder(
            applicationContext
        )
            .setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(icon1)
            .setStyle(Notification.BigTextStyle().bigText(message))
            .setColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
            .setContentTitle(title)
            .setContentText(message)
            .setSound(soundUri)
            .setDefaults(DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            // .setDefaults(DEFAULT_SOUND or DEFAULT_VIBRATE)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(CHANNEL_ID)
            notificationManager!!.createNotificationChannel(notificationChannel)
        }
        notification = notificationBuilder.build()
        notificationManager!!.notify(
            ((Date().getTime() / 1000L % Int.MAX_VALUE).toInt()),
            notification
        )
    }

}
