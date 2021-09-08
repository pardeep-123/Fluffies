package com.puppypedia.utils.helper.others

class Constants {
    companion object {
        const val BASE_URL = "http://202.164.42.227:7700/api/"
        const val USER_IMAGE_URL = "http://202.164.42.227:7700/assets/images/users/"
        const val PET_IMAGE_URL = "http://202.164.42.227:7700/assets/images/pets/"

        val success_code = 200
        val errorCode = 403
        val Device_Type = "1"      //(1 => Android, 2 => IOS)
        var SecurityKey = ""
        var SecurityKeyValue = "__SecureDappreciate"
        val token = "token"
        val AuthKey = "Authorization"
        const val TYPE_USER = "1"
        const val ITEM_DETAILS = "items_details"

        const val SignUp = BASE_URL + "signUp"
        const val FileUpload = BASE_URL + "file_upload"
        const val Login = BASE_URL + "login"
        const val AddPuppies = BASE_URL + "add_puppies"
        const val AboutUs = BASE_URL + "about"
        const val Terms = BASE_URL + "terms"
        const val Privacy = BASE_URL + "privacy_policy"
        const val NotificationListing = BASE_URL + "notificationListing"
        const val NotificationOnOff = BASE_URL + "notification_on_off"
        const val ChangePassword = BASE_URL + "changePassword"
        const val Profile = BASE_URL + "profile"
        const val EditProfile = BASE_URL + "edit_profile"
        const val Logout = BASE_URL + "logout"

        const val ForgotPassword = BASE_URL + "forgotPassword"

        const val HomePageListing = BASE_URL + "homePageListing"

    }
}