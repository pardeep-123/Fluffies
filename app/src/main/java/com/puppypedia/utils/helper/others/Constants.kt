package com.puppypedia.utils.helper.others

class Constants {
    companion object {
        //  Live URL   https://app.puppypediaapp.com/api
        // Local URL http://202.164.42.227:7700/api/

        /* const val BASE_URL = "http://202.164.42.227:7700/api/"
         const val IMAGE_URL = "http://202.164.42.227:7700"
         const val PET_IMAGE_URL = "http://202.164.42.227:7700/"*/

        /*    const val BASE_URL = "https://app.puppypediaapp.com/api/"
            const val IMAGE_URL = "https://app.puppypediaapp.com"
            const val PET_IMAGE_URL = "https://app.puppypediaapp.com/"*/


        ///////////////CHANGED Live URL
        const val BASE_URL = "https://app.puppypediaapp.com/api/"
        const val IMAGE_URL = "https://app.puppypediaapp.com"
        const val PET_IMAGE_URL = "https://app.puppypediaapp.com/"
        const val PET_IMAGE_URL2 = "https://app.puppypediaapp.com"


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
        const val GetPetProfile = BASE_URL + "get_pets"
        const val get_pet_post = BASE_URL + "get_pet_post"
        const val EditProfile = BASE_URL + "edit_profile"
        const val edit_pet_post = BASE_URL + "edit_pet_post"
        const val EditPetProfile = BASE_URL + "edit_pet"
        const val Logout = BASE_URL + "logout"
        const val ForgotPassword = BASE_URL + "forgotPassword"
        const val HomePageListing = BASE_URL + "homePageListing"
        const val AddWeightChart = BASE_URL + "add_weight_chart"
        const val GetWeight = BASE_URL + "get_weight"
        const val GetStatsData = BASE_URL + "get_stats_data"
        const val HomeApi = BASE_URL + "home_api"
        const val AddReminder = BASE_URL + "add_reminder"
        const val GetReminders = BASE_URL + "get_reminders"
        const val delete_pet_data = BASE_URL + "delete_pet_data"
        const val delete_pet_weight = BASE_URL + "delete_pet_weight"
        const val RemindersOnOff = BASE_URL + "reminder_on_off"
        const val upload_pet = BASE_URL + "upload_pet"
        fun gender(value: Int): String {
            if (value == 0) {
                return "Male"
            } else {
                return "Female"
            }
        }
    }
}