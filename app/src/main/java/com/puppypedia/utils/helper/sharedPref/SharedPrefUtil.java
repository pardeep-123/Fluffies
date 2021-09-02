package com.puppypedia.utils.helper.sharedPref;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.puppypedia.utils.helper.MyApplication;


public class SharedPrefUtil {

    public static final String NAME = "name";
    public static final String User_Type = "user_type";
    public static final String EMAIL = "email";
    public static final String IMAGE = "profileImage";
    public static final String LOGIN_TYPE = "loginType";
    public static final String USER_ID = "userId";
    public static final String PASSWORD = "password";
    public static final String DEVICE_TOKEN = "deviceToken";
    public static final String AUTH_TOKEN = "authToken";
    public static final String COUNTRY_CODE_NAME = "CountryCodeName";
    public static final String LOGIN = "login";
    public static final String BADGE = "badge";
    public static final String NOTIFY = "notify";
    public static final String NOTIFY_ICON = "notifyIcon";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String LOCATON = "location";

    public static final String PHONE_NUMBER = "phone_number";
    public static final String COUNTRY_CODE = "country_code";
    public static final String CITY = "city";
    public static final String IS_NOTIFICATION = "is_notification";
    public static final String ROLE = "role";
    public static final String RANDOM_ID = "random_id";
    public static final String IS_FIRST_TIME = "is_first_time";

    /**
     * Name of the preference file
     */
    private static final String APP_PREFS = "application_preferences";
    private static SharedPreferences mSharedPreferences;
    private static SharedPrefUtil instance;
    private Context mContext;
    private SharedPreferences.Editor mEditor;

    public SharedPrefUtil(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SharedPrefUtil(context);
        }
    }

    public static SharedPrefUtil getInstance() {
        if (instance == null) {
            instance = new SharedPrefUtil(MyApplication.Companion.getInstance());
        }
        return instance;
    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * Save a string into shared preference
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     */
    public void saveString(String key, String value) {

        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    /**
     * Save a int into shared preference
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     */
    public void saveInt(String key, int value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    /**
     * Save a boolean into shared preference
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     */
    public void saveBoolean(String key, boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or null.
     * Throws ClassCastException if there is a preference with this name that is not a String.
     */
    public String getString(String key) {
        //    mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, "");
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * Throws ClassCastException if there is a preference with this name that is not a String.
     */
    public String getString(String key, String defaultValue) {
        //    mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, defaultValue);
    }

    /**
     * Retrieve a int value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0.
     * Throws ClassCastException if there is a preference with this name that is not a int.
     */
    public int getInt(String key) {
        // mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, 0);
    }

    /**
     * Retrieve a int value from the preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * Throws ClassCastException if there is a preference with this name that is not a int.
     */
    public int getInt(String key, int defaultValue) {
        //     mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        //     mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key, false);
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * Throws ClassCastException if there is a preference with this name that is not a boolean.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }



    /* save  is first time login */

    public void saveDeviceToken(String token) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(DEVICE_TOKEN, token);
        mEditor.apply();
    }

    public String getDeviceToken() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(DEVICE_TOKEN, "");
    }

    public void saveRole(int theme) {

        mEditor = mSharedPreferences.edit();
        mEditor.putInt(ROLE, theme);
        mEditor.apply();
    }

    public void saveRandomId(String random) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(RANDOM_ID, random);
        mEditor.apply();
    }

    public String getRandomId() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(RANDOM_ID, "");
    }

    /**
     * get the current device token
     *
     * @return Returns the last update device token got from firebase.
     */
    public int getRole() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(ROLE, 0);
    }

    public void saveFirstTime(String firstTime) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(IS_FIRST_TIME, firstTime);
        mEditor.apply();
    }

    /**
     * get the is first time login *
     *
     * @return Returns the last update device token got from firebase.
     */

    public String getFirstTime() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(IS_FIRST_TIME, "");
    }

    /**
     * save the userId of current user using the application
     *
     * @param userId this is the userId of the user
     */
    public void saveUserId(String userId) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_ID, userId);
        mEditor.apply();
    }

    /**
     * get current login user's userId .
     *
     * @return Returns the last userId saved.
     */
    public String getUserId() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(USER_ID, "");
    }

    /**
     * save the latest authorization token of the user
     *
     * @param authToken authoriztion token of the user.
     */
    public void saveAuthToken(String authToken) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(AUTH_TOKEN, authToken);
        mEditor.apply();
    }

    /**
     * get the authorization of the user
     */
    public String getAuthToken() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(AUTH_TOKEN, "");
    }

    /* Set COUNTRY_CODE_NAME*/
    public void saveCountryCodeName(String countryCodeName) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(COUNTRY_CODE_NAME, countryCodeName);
        mEditor.apply();
    }

    /**
     * get the COUNTRY_CODE_NAME of the user
     */
    public String getCountryCodeName() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(COUNTRY_CODE_NAME, "");
    }

    /**
     * @return is anyuser current login into the device accordingly true/false.
     */
    public boolean isLogin() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(LOGIN, false);
    }

    /**
     * set login state oin the device is there any user current login device
     */
    public void setLogin(boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(LOGIN, value);
        mEditor.apply();
    }

    /**
     * save name of the user
     */
    public void saveName(String name) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(NAME, name);
        mEditor.apply();
    }

    public void registerrespo(String name) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString("register", name);
        mEditor.apply();
    }

    /**
     * get name of the user
     */
    public String getregisterr() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString("register", "");
    }

    public String getName() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(NAME, "");
    }

    public String getUser_Type() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(User_Type, "");
    }

    public void saveUser_Type(String usertype) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(User_Type, usertype);
        mEditor.apply();
    }

    public void editProfilerespo(String name) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString("profile", name);
        mEditor.apply();
    }

    /**
     * get name of the user
     */
    public String getEditProfileRespone() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString("profile", "");
    }

    public void saveLocation(String location) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(LOCATON, location);
        mEditor.apply();
    }

    /**
     * get location of the user
     */
    public String getLocaton() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(LOCATON, "");
    }

    public void savePhone(String phone) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(PHONE_NUMBER, phone);
        mEditor.apply();
    }

    /**
     * get phone of the user
     */
    public String getPhone() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(PHONE_NUMBER, "");
    }

    /**
     * save image of the user
     */
    public void saveImage(String image) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(IMAGE, image);
        mEditor.apply();
    }

    /**
     * get image of the user
     */
    public String getImage() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(IMAGE, "");
    }

    public void savePassword(String password) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(PASSWORD, password);
        mEditor.apply();
    }

    public String getPassword() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(PASSWORD, "");
    }

    public void saveEmail(String email) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(EMAIL, email);
        mEditor.apply();
    }

    public String getEmail() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(EMAIL, "");
    }

    public void saveNotifyIcon(boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(NOTIFY_ICON, value);
        mEditor.apply();
    }

    public boolean getNotifyIcon() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(NOTIFY_ICON, true);
    }

    public void saveBadge(int badge) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(BADGE, badge);
        mEditor.apply();
    }

    public int getBadge() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(BADGE, 0);
    }

    public void saveNotify(int notify) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(NOTIFY, notify);
        mEditor.apply();
    }

    public int getNotify() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(NOTIFY, 0);
    }

    /**
     * Clears the shared preference file
     */
    public void clear() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        //  mSharedPreferences.edit().clear().apply();
        mEditor = mSharedPreferences.edit();
        saveLocation("");
        saveAuthToken("");
        saveUserId("");
        saveRole(0);
        saveImage("");
        savePhone("");
        mEditor.apply();

    }

    /**
     * clear all user's save data whenever user logout the device.
     */
    public void onLogout() {
        mEditor = mSharedPreferences.edit();
        if (!getPassword().equals("")) {
            mEditor.remove(USER_ID);
            mEditor.remove(AUTH_TOKEN);
            mEditor.remove(EMAIL);
            mEditor.remove(IMAGE);
            mEditor.remove(NOTIFY_ICON);
            mEditor.remove(NOTIFY);
            //  mEditor.remove(PHONE_NUMBER);
        } else {
            mEditor.remove(USER_ID);
            mEditor.remove(AUTH_TOKEN);
            mEditor.remove(NAME);
            mEditor.remove(EMAIL);
            mEditor.remove(IMAGE);
            mEditor.remove(NOTIFY_ICON);
            mEditor.remove(NOTIFY);
            //mEditor.remove(PHONE_NUMBER);
        }
        setLogin(false);
        mEditor.apply();
    }

    public void saveLatitude(String latitude) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(LATITUDE, latitude);
        mEditor.apply();
    }

    public void saveLongitude(String longitude) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(LONGITUDE, longitude);
        mEditor.apply();
    }

    public String getLatitude() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(LATITUDE, "");

    }

    public String getLongitude() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(LONGITUDE, "");

    }

    public void saveCountryCode(String countryCode) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(COUNTRY_CODE, countryCode);
        mEditor.apply();
    }

    public String getCountryCode() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(COUNTRY_CODE, "");

    }

    public void saveCity(String city) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(CITY, city);
        mEditor.apply();
    }

    public String getCity() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(CITY, "");

    }

    public void savePushNotificationStatus(String isNotification) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(IS_NOTIFICATION, isNotification);
        mEditor.apply();
    }

    public String getPushNotificationStatus() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(IS_NOTIFICATION, "");
    }

}
