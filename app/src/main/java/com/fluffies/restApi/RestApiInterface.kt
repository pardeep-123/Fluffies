package com.fluffies.restApi

import com.fluffies.model.*
import com.fluffies.ui.auth.forgotpassword.ForgotPasswordResponse
import com.fluffies.ui.auth.login.LoginResponse
import com.fluffies.ui.auth.signup.SignUpResponse
import com.fluffies.ui.commomModel.ImageUploadResponse
import com.fluffies.ui.commomModel.LogoutResponse
import com.fluffies.ui.commomModel.NotificationOnOffModel
import com.fluffies.ui.fragments.calender.CalenderGetReminderResponse
import com.fluffies.ui.fragments.calender.ReminderOnOffResponse
import com.fluffies.ui.fragments.home.HomeFragmentResponse
import com.fluffies.ui.fragments.statistics.StatisticsResponse
import com.fluffies.ui.fragments.weight.GetWeightResponse
import com.fluffies.ui.main.ui.about_us.AboutusResponse
import com.fluffies.ui.main.ui.add_record.AddPetRecordResponse
import com.fluffies.ui.main.ui.add_record.EditPetDataResponse
import com.fluffies.ui.main.ui.add_weight.AddWeightResponse
import com.fluffies.ui.main.ui.addremainder.AddReminderResponse
import com.fluffies.ui.main.ui.category_detail.BackgroundModel
import com.fluffies.ui.main.ui.category_detail.DeleteResponse
import com.fluffies.ui.main.ui.category_detail.GetPetResponse
import com.fluffies.ui.main.ui.changepassword.ChangePasswordResponse
import com.fluffies.ui.main.ui.editpetprofile.EditPetResponse
import com.fluffies.ui.main.ui.editprofile.EditProfileResponse
import com.fluffies.ui.main.ui.mypetprofile.PetProfileResponse
import com.fluffies.ui.main.ui.notification.NotificationResponse
import com.fluffies.ui.main.ui.petdetail.PetDetailResponse
import com.fluffies.ui.main.ui.profile.ProfileResponse
import com.fluffies.utils.helper.others.Constants
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*


interface RestApiInterface {

    @FormUrlEncoded
    @POST(Constants.SignUp)
    fun signUp(
        @FieldMap map: HashMap<String, String>
    ): Observable<SignUpResponse>

    @Multipart
    @POST(Constants.FileUpload)
    fun fileUpload(
        @PartMap map: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Observable<ImageUploadResponse>

    // Add Health
    @Multipart
    @POST(Constants.addHealthDetail)
    fun addHealthDetail(
        @PartMap map: HashMap<String, RequestBody>
      //  @Part image: MultipartBody.Part,
       // @Part image1: MultipartBody.Part
    ): Observable<AddHealthDetail>

    // Edit Health
    @Multipart
    @POST(Constants.editHealthDetail)
    fun editHealthDetail(
        @PartMap map: HashMap<String, RequestBody>
        //  @Part image: MultipartBody.Part,
        // @Part image1: MultipartBody.Part
    ): Observable<AddHealthDetail>

    // to add pictures
    @FormUrlEncoded
    @POST(Constants.addPicture)
    fun addPicture(
        @FieldMap map: HashMap<String, String>
        //  @Part image: MultipartBody.Part,
        // @Part image1: MultipartBody.Part
    ): Observable<NewAddModel>


    // to add pictures
    @FormUrlEncoded
    @POST(Constants.addLifeEvent)
    fun addLifeEvent(
        @FieldMap map: HashMap<String, String>
        //  @Part image: MultipartBody.Part,
        // @Part image1: MultipartBody.Part
    ): Observable<AddLifeEventModel>

    // to delete life events

    // to add pictures
    @FormUrlEncoded
    @POST(Constants.delLifeEvent)
    fun delLifeEvent(
        @FieldMap map: HashMap<String, Int>
        //  @Part image: MultipartBody.Part,
        // @Part image1: MultipartBody.Part
    ): Observable<DeleteResponse>

    // to get pictures
    @Multipart
    @POST(Constants.getPicture)
    fun getPicture(
        @PartMap map: HashMap<String, RequestBody>
        //  @Part image: MultipartBody.Part,
        // @Part image1: MultipartBody.Part
    ): Observable<GetImageModel>


    // to get pictures
    @FormUrlEncoded
    @POST(Constants.getLifeEvent)
    fun getLifeEvent(
        @FieldMap map: HashMap<String, String>
        // @Part image: MultipartBody.Part,
        // @Part image1: MultipartBody.Part
    ): Observable<GetLifeEventModel>


    // to get background picture
    @GET(Constants.getBackground)
    fun getBackground(): Observable<BackgroundModel>

    // to get pictures
    @FormUrlEncoded
    @POST(Constants.delPicture)
    fun delPicture(
        @FieldMap map: HashMap<String, String>
        //  @Part image: MultipartBody.Part,
        // @Part image1: MultipartBody.Part
    ): Observable<DeleteResponse>


    @Multipart
    @POST(Constants.FileUpload)
    fun fileUploadmultiple(
        @PartMap map: HashMap<String, RequestBody>,
        @Part image: ArrayList<MultipartBody.Part?>
    ): Observable<ImageUploadResponse>


    @FormUrlEncoded
    @POST(Constants.Login)
    fun login(
        @FieldMap map: HashMap<String, String>
    ): Observable<LoginResponse>

    @FormUrlEncoded
    @POST(Constants.AddPuppies)
    fun addPuppy(
        @FieldMap map: HashMap<String, String>
    ): Observable<PetDetailResponse>

    @GET(Constants.AboutUs)
    fun aboutUs(): Observable<AboutusResponse>

    @GET(Constants.Privacy)
    fun privacypolicy(): Observable<AboutusResponse>


    @GET(Constants.Terms)
    fun terms(): Observable<AboutusResponse>


    @GET(Constants.NotificationListing)
    fun notiListing(): Observable<NotificationResponse>

    @FormUrlEncoded
    @POST(Constants.NotificationOnOff)
    fun notiOnOff(
        @Field("status") status: String
    ): Observable<NotificationOnOffModel>

    @FormUrlEncoded
    @POST(Constants.ChangePassword)
    fun changePassword(
        @FieldMap map: HashMap<String, String>
    ): Observable<ChangePasswordResponse>

    @GET(Constants.Profile)
    fun apiProfile(): Observable<ProfileResponse>

    @GET(Constants.GetPetProfile)
    fun apiPetProfile(): Observable<PetProfileResponse>

/*  @GET(Constants.get_pet_post)
    fun apiPetData(): Observable<GetPetResponse>*/

    @FormUrlEncoded
    @POST(Constants.get_pet_post)
    fun apiPetData(
        @Field("petid") petid: String
    ): Observable<GetPetResponse>


    @FormUrlEncoded
    @POST(Constants.category_detail)
    fun apiCategoryDetails(
        @Field("id") id: String
    ): Observable<CategoryModel>
    @GET(Constants.HomeApi)
    fun apiHome(): Observable<HomeFragmentResponse>


    @GET(Constants.Logout)
    fun apiLogout(): Observable<LogoutResponse>

    @FormUrlEncoded
    @POST(Constants.EditProfile)
    fun editProfile(
        @FieldMap map: HashMap<String, String>
    ): Observable<EditProfileResponse>

    @FormUrlEncoded
    @POST(Constants.edit_pet_post)
    fun editPetPost(
        @FieldMap map: HashMap<String, String>
    ): Observable<EditPetDataResponse>


    @Multipart
    @POST(Constants.EditProfile)
    fun editUserProfileWithImage(
        @PartMap map: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Observable<EditProfileResponse>

    @FormUrlEncoded
    @POST(Constants.EditPetProfile)
    fun editPetProfile(
        @FieldMap map: HashMap<String, String>
    ): Observable<EditPetResponse>

    @Multipart
    @POST(Constants.EditPetProfile)
    fun editPetProfileWithImage(
        @PartMap map: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Observable<EditPetResponse>

    @FormUrlEncoded
    @POST(Constants.ForgotPassword)
    fun apiforgotPassword(
        @FieldMap map: HashMap<String, String>
    ): Observable<ForgotPasswordResponse>


    @FormUrlEncoded
    @POST(Constants.AddWeightChart)
    fun apiAddPetWeight(
        @FieldMap map: HashMap<String, String>
    ): Observable<AddWeightResponse>

    @FormUrlEncoded
    @POST(Constants.GetWeight)
    fun apiGetPetWeight(
        @Field("id") id: String
    ): Observable<GetWeightResponse>

    @FormUrlEncoded
    @POST(Constants.GetStatsData)
    fun apiPetChart(
        @Field("datetype") datetype: String,
        @Field("petid") petid: String
    ): Observable<StatisticsResponse>


    @FormUrlEncoded
    @POST(Constants.AddReminder)
    fun apiAddReminder(
        @FieldMap map: HashMap<String, String>
    ): Observable<AddReminderResponse>

    /*   @Multipart
       @POST(Constants.AddPuppies)
       fun addPuppy(
        @Part map: HashMap<String, String>,
        @Part image: MultipartBody.Part
       ): Observable<PetDetailResponse?>
   */


    @FormUrlEncoded
    @POST(Constants.GetReminders)
    fun apiGetReminders(
        @Field("datetime") datetime: String
    ): Observable<CalenderGetReminderResponse>

    @GET(Constants.getHealthDetail)
    fun getHealthDetail(): Observable<GetHealthListModel>


    @FormUrlEncoded
    @POST(Constants.delete_pet_data)
    fun apideletePet(
        @Field("petid") petid: String,
        @Field("post_id") post_id: String,
    ): Observable<DeleteResponse>

    @FormUrlEncoded
    @POST(Constants.deleteHealthDetail)
    fun apideleteHealthDetail(
        @Field("pet_id") petid: String,
        @Field("health_id") health_id: String,
    ): Observable<DeleteResponse>

    @FormUrlEncoded
    @POST(Constants.delete_pet_weight)
    fun apiWeightPet(
        @Field("weightId") weightId: String
    ): Observable<DeleteResponse>


    @FormUrlEncoded
    @POST(Constants.delete_pet_data)
    fun apideletePetImages(
        @Field("petid") petid: String,
        @Field("post_id") post_id: String,
        @Field("image_id") image_id: String,
    ): Observable<DeleteResponse>

    @FormUrlEncoded
    @POST(Constants.RemindersOnOff)
    fun apiReminderOnOff(
        @Field("isRemind") isRemind: String,
        @Field("reminderid") reminderid: String
    ): Observable<ReminderOnOffResponse>

    @FormUrlEncoded
    @POST(Constants.upload_pet)
    fun addPuppyDescription(
        @FieldMap map: HashMap<String, String>
    ): Observable<AddPetRecordResponse>


    @FormUrlEncoded
    @POST(Constants.deletePetReminder)
    fun deletePetReminder(
        @FieldMap map: HashMap<String, String>
    ): Observable<DeleteResponse>

}