package com.puppypedia.utils.helper.others;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

import com.puppypedia.R;
import com.puppypedia.utils.helper.MyApplication;
import com.tapadoo.alerter.Alerter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by admin on 25-09-2018.
 */

public class ValidationsClass {

    //    private static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
    private static final String EMAIL_PATTERN =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                    "(?:[\\x01-\\x07\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01" +
                    "-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x07\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static ValidationsClass validations = null;
    private Activity mActivity;

    public ValidationsClass() {
    }

    public static ValidationsClass getInstance() {
        if (validations == null) {
            validations = new ValidationsClass();
        }
        return validations;
    }

    public boolean checkStringNull(String string) {
        return string == null || string.equals("null") || string.isEmpty();
    }

    public boolean checkObjectNull(Object obj) {
        return !(obj == null);
    }

    // validate first name
    public boolean isValidFirstName(String mFirstName) {
        return mFirstName.matches("^[A-Za-z]");
//        return mFirstName.matches("[A-Z][a-zA-Z]*");
    } // end method validateFirstName

    // validate last name
    public boolean isValidLastName(String mLastName) {
        return mLastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
    } // end method validateLastName

    public boolean isValidPhone(String mPhone, int minLenght, int maxLenght) {
        return !(mPhone.length() < minLenght || mPhone.length() > maxLenght) && Patterns.PHONE.matcher(mPhone).matches();
    }

    public boolean isValidEmail(String mEmail) {
        if (checkStringNull(mEmail))
            return false;
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(mEmail);
        return matcher.matches();
    }


    public boolean isValidPassword(String mPassword) {
        return mPassword.matches("^.{6,}$");
    }

    public boolean isValidPhone(String mPhoneNumber) {
        return mPhoneNumber.matches("^.{10,10}$");
    }

    public boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
/*
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
*/
        else return false;

    }

    public boolean isValidTraitName(String mPassword) {
        return mPassword.matches("^.{3,}$");
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.Companion.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null)
            activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void Alerter(Activity activity, String msg) {
        Alerter.create(activity)
                .setText(msg)
                .enableSwipeToDismiss()
                .setBackgroundColorRes(R.color.red)
                .show();
    }

    public void AlerterGreen(Activity activity, String msg) {
        Alerter.create(activity)
                .setText(msg)
                .enableSwipeToDismiss()
                .setBackgroundColorRes(R.color.green)
                .show();
    }

    public RequestBody createPartFromString(String string) {
        return RequestBody.create(
                MultipartBody.FORM, string);
    }

    public MultipartBody.Part prepareFilePart(String partName, File fileUri) {
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        fileUri
                );

        return MultipartBody.Part.createFormData(partName, fileUri.getName(), requestFile);
    }
    /*public MultipartBody.Part prepareFileVideoPart(String partName, File fileUri) {
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("video/*"),
                        fileUri
                );

        return MultipartBody.Part.createFormData(partName, fileUri.getName(), requestFile);
    }*/

    public MultipartBody.Part prepareFileVideoPart(String partName, File fileUri) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileUri);
        return MultipartBody.Part.createFormData(partName, fileUri.getName(), requestFile);
    }

    public String convertTimeStempToTime(long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000);
        DateFormat outputFormat = new SimpleDateFormat("hh:mm a");
        //outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return outputFormat.format(cal.getTime());
    }


    /**
     * Check whether Google Play Services are available.
     * <p>
     * If not, then display dialog allowing user to update Google Play Services
     *
     * @return true if available, or false if not
     */
  /*  public boolean checkGooglePlayServicesAvailable(Activity mActivity) {
        final int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MyApplication.getInstance().getApplicationContext());
        if (status == ConnectionResult.SUCCESS)
            return true;
        Log.e("Google Play Services", "Google Play Services not available: " + GoogleApiAvailability.getInstance().getErrorString(status));
        if (GoogleApiAvailability.getInstance().isUserResolvableError(status)) {
            final Dialog errorDialog = GoogleApiAvailability.getInstance().getErrorDialog(mActivity, status, 1);
            if (errorDialog != null)
                errorDialog.show();
        }
        return false;
    }*/


}
