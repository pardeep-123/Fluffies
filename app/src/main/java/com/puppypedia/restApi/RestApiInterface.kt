package com.puppypedia.restApi

import com.puppypedia.ui.auth.login.LoginResponse
import com.puppypedia.ui.auth.signup.SignUpResponse
import com.puppypedia.ui.commomModel.CommonModel
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.commomModel.LogoutResponse
import com.puppypedia.ui.commomModel.NotificationOnOffModel
import com.puppypedia.ui.fragments.home.HomeFragmentResponse
import com.puppypedia.ui.main.ui.about_us.AboutusResponse
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
    @POST(Constants.EditPetProfile)
    fun editPetProfile(
        @FieldMap map: HashMap<String, String>
    ): Observable<EditPetResponse>


    @FormUrlEncoded
    @POST(Constants.ForgotPassword)
    fun apiforgotPassword(
        @FieldMap map: HashMap<String, String>
    ): Observable<CommonModel>


    @FormUrlEncoded
    @POST(Constants.AddWeightChart)
    fun apiAddPetWeight(
        @FieldMap map: HashMap<String, String>
    ): Observable<CommonModel>

    @FormUrlEncoded
    @POST(Constants.GetWeight)
    fun apiGetPetWeight(
        @FieldMap map: HashMap<String, String>
    ): Observable<CommonModel>


    /*   @Multipart
       @POST(Constants.AddPuppies)
       fun addPuppy(
        @Part map: HashMap<String, String>,
        @Part image: MultipartBody.Part
       ): Observable<PetDetailResponse?>
   */
    /*

     @GET(Constants.AboutUs)
     fun aboutUs(): Observable<ModelAboutus>

     @GET(Constants.Terms)
     fun terms(): Observable<ModelTerms>

     @GET(Constants.Privacy)
     fun privacy(): Observable<ModelPrivacy>

     @GET(Constants.Logout)
     fun logout(): Observable<CommonResponse>

     @FormUrlEncoded
     @POST(Constants.ChangePassword)
     fun changePassword(
         @FieldMap map: HashMap<String, String>
     ): Observable<CommonResponse>


     @FormUrlEncoded
     @POST(Constants.ForgotPassword)
     fun forgotPassword(
         @FieldMap map: HashMap<String, String>
     ): Observable<CommonResponse>

     @GET(Constants.Profile)
     fun profile(): Observable<CommonResponse>
 */

}