package com.puppypedia.utils.helper.others

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.puppypedia.R
import com.puppypedia.listeners.OnNoInternetConnectionListener
import com.puppypedia.ui.auth.login.LoginActivity
import com.tapadoo.alerter.Alerter
import com.valdesekamdem.library.mdtoast.MDToast
import java.text.SimpleDateFormat
import java.util.*

object Helper {

    //--------------------------Keyboard Hide----------------------//
    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken, 0
        )
    }

    //------------------------Return Date in String------------------//
    fun longToDate(timeInMillis: Long): String {

        val dateFormat = "dd/MM/yy" //In which you need put here
        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
        return sdf.format(timeInMillis)
    }

    fun getCurrentDay(): String {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]
        return day.toString()
    }

    @JvmStatic
    fun showToast(context: Context, msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun showSuccessToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun removeExtraString(toString: String): String {
        return toString
            .replace("[", "") //remove the right bracket
            .replace("]", "") //remove the left bracket
            .trim { it <= ' ' }
    }


    @JvmStatic
    fun showErrorAlert(context: Activity, msg: String) {
        /* Alerter.create(context)
             .setTitle(context.getString(R.string.error_))
             .setTitleAppearance(R.style.AlertTextAppearanceTitle)
             .setText(msg)
             .setTextAppearance(R.style.AlertTextAppearanceText)
             .setBackgroundColorRes(R.color.colorRed)
             .show()*/
        val mdToast = MDToast.makeText(context, msg, 5000, 3)
        mdToast.setIcon(null)
        mdToast.show()

    }

    @JvmStatic
    fun showSuccessAlert(context: Activity, msg: String) {
        Alerter.create(context)
            .setText(msg)
            .setDuration(5000)
            .setTextAppearance(R.style.AlertTextAppearanceText)
            .setBackgroundColorRes(R.color.blue)
            .show()
    }


    @JvmStatic
    fun showNoInternetAlert(
        context: Activity,
        msg: String,
        listener: OnNoInternetConnectionListener
    ) {
        Alerter.create(context)
            .setTitle(context.getString(R.string.error_))
            .setTitleAppearance(R.style.AlertTextAppearanceTitle)
            .setText(msg)
            .setTextAppearance(R.style.AlertTextAppearanceText)
            .setBackgroundColorRes(R.color.colorPrimary)
            .addButton(
                context.getString(R.string.retry),
                R.style.AlertButton,
                View.OnClickListener {
                    listener.onRetryApi()
                })
            .show()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    @JvmStatic
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null
    }

    @SuppressLint("Recycle")
    fun getAbsolutePath(activity: Context, uri: Uri): String {
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection = arrayOf("_data")
            val cursor: Cursor?
            try {
                cursor = activity.contentResolver.query(uri, projection, null, null, null)
                val columnIndex = cursor!!.getColumnIndexOrThrow("_data")
                if (cursor.moveToFirst()) {
                    return cursor.getString(columnIndex)
                }
            } catch (e: Exception) {
                // Eat it
                e.printStackTrace()
            }
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path!!
        }
        return ""
    }

    fun guestDialog(context: Activity) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.guest_dialog)

        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window!!.setGravity(Gravity.BOTTOM)

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnLogin = dialog.findViewById<Button>(R.id.btnLogin)
        val llCancel = dialog.findViewById<LinearLayout>(R.id.llCancel)
        llCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnLogin.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
            context.finishAffinity()
        }

        dialog.show()
    }

    fun convertToCustomFormat(dateStr: String?): String {
        val utc = TimeZone.getTimeZone("UTC")
        val sourceFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
        val destFormat = SimpleDateFormat("HH:mm:ss")
        sourceFormat.timeZone = utc
        val convertedDate = sourceFormat.parse(dateStr)
        Log.d("tttttt", destFormat.format(convertedDate))
        return destFormat.format(convertedDate)

    }

    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("HH:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate
    }

    fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + (Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta))))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}