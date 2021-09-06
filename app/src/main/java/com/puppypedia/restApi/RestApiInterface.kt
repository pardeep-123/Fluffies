package com.puppypedia.restApi

import com.puppypedia.ui.auth.login.LoginResponse
import com.puppypedia.ui.auth.signup.SignUpResponse
import com.puppypedia.ui.commomModel.NotificationOnOffModel
import com.puppypedia.ui.main.ui.about_us.AboutusResponse
import com.puppypedia.ui.main.ui.notification.NotificationResponse
import com.puppypedia.ui.main.ui.petdetail.PetDetailResponse
import com.puppypedia.utils.helper.others.Constants
import io.reactivex.Observable
import retrofit2.http.*
import java.util.*


interface RestApiInterface {

    @FormUrlEncoded
    @POST(Constants.SignUp)
    fun signUp(
        @FieldMap map: HashMap<String, String>
    ): Observable<SignUpResponse>

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