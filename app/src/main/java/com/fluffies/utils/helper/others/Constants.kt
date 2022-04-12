package com.fluffies.utils.helper.others

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
        const val category_detail = BASE_URL + "category_detail"
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
        const val deletePetReminder = BASE_URL + "delete_pet_reminder"
        const val addHealthDetail = BASE_URL + "add_health_detail"
        const val getHealthDetail = BASE_URL + "get_health_detail"
        const val deleteHealthDetail = BASE_URL + "delete_health_detail"
        const val editHealthDetail = BASE_URL + "edit_health_detail"
        const val addPicture = BASE_URL + "add_picture"
        const val getPicture = BASE_URL + "get_picture"
        const val delPicture = BASE_URL + "del_picture"
        const val getLifeEvent = BASE_URL + "get_life_event"
        const val addLifeEvent = BASE_URL + "add_life_event"
        const val delLifeEvent = BASE_URL + "del_life_event"
        const val getBackground = BASE_URL + "get_background"
        fun gender(value: Int): String {
            if (value == 0) {
                return "Male"
            } else {
                return "Female"
            }
        }

        fun petType(value: Int): String {
            if (value == 1) {
                return "Dog"
            } else {
                return "Cat"
            }
        }

    }
}