package com.puppypedia.utils.helper.others

class Constants {
    companion object {
        const val BASE_URL = "http://202.164.42.227:7700/api/"

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

        const val ForgotPassword = BASE_URL + "forgotPassword"

        const val HomePageListing = BASE_URL + "homePageListing"
        const val HomePageSkipListing = BASE_URL + "guest/homePageListing"
        const val RestaurantDetail = BASE_URL + "restaurantDetail"
        const val RestaurantGuestDetail = BASE_URL + "guest/restaurantDetail"
        const val guestProductDetail = BASE_URL + "guest/productDetail"
        const val productDetail = BASE_URL + "user/productDetail"
        const val CartDetail = BASE_URL + "cartDetail"
        const val GuestCartDetail = BASE_URL + "guestCartDetail"
        const val ExtraNotificationOnOff = BASE_URL + "extraNotificationOnOff"
        const val GetBlogList = BASE_URL + "getBlogList"
        const val AddToCart = BASE_URL + "addToCart"
        const val AddToCartGuest = BASE_URL + "addToCartGuest"
        const val ViewAllListing = BASE_URL + "viewAllListing"
        const val ViewAllListingGuest = BASE_URL + "guest/viewAllListing"
        const val RestaurantFav = BASE_URL + "restaurantFav"
        const val RestaurantFavList = BASE_URL + "restaurantFavList"
        const val FilterListing = BASE_URL + "filterListing"
        const val FilterGuestListing = BASE_URL + "guest/filterListing"
        const val GetProfile = BASE_URL + "getProfile"


        const val PrivacyPolicy = BASE_URL + "privacyPolicy"
        const val FaqList = BASE_URL + "getFaqList"
        const val HelpList = BASE_URL + "getHelpList"
        const val AddCard = BASE_URL + "addCard"
        const val AllCards = BASE_URL + "allCards"
        const val DeleteCard = BASE_URL + "deleteCard"
        const val UpdateCard = BASE_URL + "updateCard"
        const val SetDefaultCard = BASE_URL + "setDefaultCard"
        const val AddUserAddress = BASE_URL + "addUserAddress"
        const val UserAddressListing = BASE_URL + "userAddressListing"
        const val UpdateUserAddress = BASE_URL + "updateUserAddress"
        const val SetDefaultUserAddress = BASE_URL + "setDefaultUserAddress"
        const val DeleteUserAddress = BASE_URL + "deleteUserAddress"
        const val Logout = BASE_URL + "logout"
        const val CategoryListData = BASE_URL + "categoryListData"
        const val GuestOrderPlace = BASE_URL + "guest/orderPlace"
        const val ConvertOrderPickupToDelivery = BASE_URL + "user/convertOrderPickupToDelivery"
        const val GetPayPalWebviewLink = BASE_URL + "getPayPalWebviewLink"
        const val OrderList = BASE_URL + "user/orderList"
        const val GuestOrderList = BASE_URL + "guest/OrderList"
        const val DriverReview = BASE_URL + "user/driverReview"
        const val OrderDetail = BASE_URL + "user/orderDetail"
        const val GuestOrderDetail = BASE_URL + "guest/OrderDetail"
        const val CancelOrder = BASE_URL + "user/cancelOrder"
        const val guestCancelOrder = BASE_URL + "guest/cancelOrder"
    }
}