package com.puppypedia.restApi

import com.puppypedia.ui.auth.forgotpassword.ForgotPasswordResponse
import com.puppypedia.ui.auth.login.LoginResponse
import com.puppypedia.ui.auth.signup.SignUpResponse
import com.puppypedia.ui.commomModel.CommonModel
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.commomModel.LogoutResponse
import com.puppypedia.ui.commomModel.NotificationOnOffModel
import com.puppypedia.ui.fragments.calender.CalenderGetReminderResponse
import com.puppypedia.ui.fragments.calender.ReminderOnOffResponse
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.ui.fragments.statistics.StatisticsResponse
import com.puppypedia.ui.fragments.weight.GetWeightResponse
import com.puppypedia.ui.main.ui.about_us.AboutusResponse
import com.puppypedia.ui.main.ui.add_record.AddPetRecordResponse
import com.puppypedia.ui.main.ui.add_weight.AddWeightResponse
import com.puppypedia.ui.main.ui.addremainder.AddReminderResponse
import com.puppypedia.ui.main.ui.category_detail.GetPetResponse
import com.puppypedia.ui.main.ui.changepassword.ChangePasswordResponse
import com.puppypedia.ui.main.ui.editpetprofile.EditPetResponse
import com.puppypedia.ui.main.ui.editprofile.EditProfileResponse
import com.puppypedia.ui.main.ui.mypetprofile.PetProfileResponse
import com.puppypedia.ui.main.ui.notification.NotificationResponse
import com.puppypedia.ui.main.ui.petdetail.PetDetailResponse
import com.puppypedia.ui.main.ui.profile.ProfileResponse
import com.puppypedia.utils.helper.others.Constants
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

    @GET(Constants.HomeApi)
    fun apiHome(): Observable<HomeFragmentResponse>


    @GET(Constants.Logout)
    fun apiLogout(): Observable<LogoutResponse>

    @FormUrlEncoded
    @POST(Constants.EditProfile)
    fun editProfile(
        @FieldMap map: HashMap<String, String>
    ): Observable<EditProfileResponse>

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

    @FormUrlEncoded
    @POST(Constants.delete_pet_data)
    fun apideletePet(
        @Field("id") id: String,
        @Field("post_id") post_id: String,
    ): Observable<CommonModel>

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

}