package com.fluffies.model


import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("body")
    val body: Body?,
    @SerializedName("code")
    val code: Int, // 200
    @SerializedName("msg")
    val msg: String, // Get Category profile 
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Body(
        @SerializedName("createdAt")
        val createdAt: String, // 2021-12-31T09:49:07.000Z
        @SerializedName("description")
        val description: String, // <div>https://www.google.com&nbsp; &nbsp; &nbsp;</div><div>&nbsp;</div><div>&nbsp;</div><div>Project Name : Yemisee&nbsp;</div><div>Scrum Notes &amp; Status :&nbsp;</div><div>Android Status : profile setup screens done</div><div>home bottombar design done</div><div>settings screen done</div><div>notification screen in progress</div><div>Today's Task&nbsp;: Setting portal and Profile sect</div><div>&nbsp;</div><div><a href="https://www.google.com/">https://www.google.com/</a></div><div>&nbsp;</div><div><a href="http://www.google.com">www.google.com</a></div><div>&nbsp;</div><div>htpps://www.google.com</div><div>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div>
        @SerializedName("id")
        val id: Int, // 34
        @SerializedName("image")
        val image: String, // /assets/images/categories/edb09fa2-098a-4917-965a-6ae49de05216.png
        @SerializedName("logo")
        val logo: String, // /assets/images/logo/d48b0304-cfe6-4017-9e06-18f8458653f4.jpeg
        @SerializedName("name")
        val name: String, // Pet Records
        @SerializedName("status")
        val status: Int, // 1
        @SerializedName("updatedAt")
        val updatedAt: String // 2022-04-07T12:50:51.000Z
    )
}