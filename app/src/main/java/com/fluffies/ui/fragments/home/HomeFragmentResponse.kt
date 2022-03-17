package com.fluffies.ui.fragments.home


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HomeFragmentResponse(
    val success: Boolean, // 1
    val code: Int, // 200
    val msg: String, // Home data get successfuly
    val body: Body
) : Serializable {
    data class Body(
        @SerializedName("Banners")
        val banners: List<Banner>,
        @SerializedName("Category")
        val category: List<Category>,
        @SerializedName("Pets")
        val pets: List<Pet>,
        val notificationsCount: Int // 0
    ) : Serializable {
        data class Banner(
            val id: Int, // 2
            val description: String, // vaccinations will shield our pets from are dangerous, potentially deadly, and, thankfully, mostly preventable.
            val image: String, // /assets/images/banners/9acf375e-1610-44fe-ad48-4ef17d8e6adf.jpeg
            val status: Int, // 1
            val createdAt: String, // 2021-08-13T12:18:27.000Z
            val updatedAt: String // 2021-10-04T10:27:38.000Z
        ) : Serializable

        data class Category(
            val id: Int, // 2
            val name: String, // Toys and Chews
            val image: String, // /assets/images/categories/d0f4af69-903f-4a52-896e-3065617997e4.png
            val logo: String,
            val description: String, // <p>Toys and Chews</p>
            val status: Int, // 1
            val createdAt: String, // 2021-08-12T10:05:38.000Z
            val updatedAt: String // 2021-09-16T09:10:35.000Z
        ) : Serializable

        data class Pet(
            val id: Int, // 106
            val name: String, // xyxy
            var selected: Boolean,
            val image: String, // /assets/images/pets/1633521213921-file.jpg
            val type : Int
        ) : Serializable
    }
}

/*
{
  "success": true,
  "code": 200,
  "msg": "Home data get successfuly",
  "body": {
    "Banners": [
      {
        "id": 2,
        "description": "<p><strong><span style=\"color: #2dc26b;\">Best of Pets </span></strong></p>\r\n<p>Vaccinations will shield our pets from are dangerous, potentially deadly, and, thankfully, mostly preventable.</p>",
        "image": "/assets/images/banners/9acf375e-1610-44fe-ad48-4ef17d8e6adf.jpeg",
        "status": 1,
        "createdAt": "2021-08-13T12:18:27.000Z",
        "updatedAt": "2021-10-07T05:24:50.000Z"
      },
      {
        "id": 3,
        "description": "<p><strong><span style=\"color: #e03e2d;\">Puppy Pedia</span></strong></p>\r\n<p><br />This highly infectious bacterium causes severe fits of coughing, whooping, vomiting, and, in rare cases, seizures and death. It is the primary cause of kennel cough.</p>",
        "image": "/assets/images/banners/f6315fa3-7d2f-4140-95e2-1f5f1c91797e.jpeg",
        "status": 1,
        "createdAt": "2021-08-13T12:32:50.000Z",
        "updatedAt": "2021-10-07T05:24:18.000Z"
      },
      {
        "id": 6,
        "description": "<p><strong><span style=\"color: #236fa1;\">Pets Care </span></strong></p>\r\n<p>There is a difference of opinion about having your adult dog vaccinated every year. Some vets believe too many vaccinations in adult dogs pose health risks.</p>",
        "image": "/assets/images/banners/6bafe6db-0bbd-480c-9a61-69c3fd54e6f3.jpeg",
        "status": 1,
        "createdAt": "2021-08-16T09:08:01.000Z",
        "updatedAt": "2021-10-07T09:29:09.000Z"
      }
    ],
    "Category": [
      {
        "id": 34,
        "name": "Pet Records",
        "image": "/assets/images/categories/edb09fa2-098a-4917-965a-6ae49de05216.png",
        "logo": "/assets/images/logo/d48b0304-cfe6-4017-9e06-18f8458653f4.jpeg",
        "description": "",
        "status": 1,
        "createdAt": "2021-12-31T09:49:07.000Z",
        "updatedAt": "2022-02-10T09:18:39.000Z"
      },
      {
        "id": 16,
        "name": "Weight Chart",
        "image": "/assets/images/categories/0fe36d08-2466-46af-9625-0546af6ee433.png",
        "logo": "",
        "description": "<p>Weight Chart</p>",
        "status": 1,
        "createdAt": "2021-09-17T04:55:32.000Z",
        "updatedAt": "2021-10-06T12:28:22.000Z"
      },
      {
        "id": 15,
        "name": "Grooming/Cosmetics",
        "image": "/assets/images/categories/f9cd6909-21c4-4f39-8114-77d6e2732e29.png",
        "logo": "/assets/images/logo/a061da41-04a2-4df0-a031-019c0b0c1496.png",
        "description": "",
        "status": 1,
        "createdAt": "2021-08-17T12:26:08.000Z",
        "updatedAt": "2021-10-07T08:02:21.000Z"
      },
      {
        "id": 13,
        "name": "Other Essentials",
        "image": "/assets/images/categories/b59f0ac4-1d67-4ab3-9569-b645050b7ec8.png",
        "logo": "/assets/images/logo/12b8be93-6ea5-44d7-8ddd-ead1abbcb9b3.png",
        "description": "",
        "status": 1,
        "createdAt": "2021-08-17T12:00:49.000Z",
        "updatedAt": "2021-10-07T08:03:42.000Z"
      },
      {
        "id": 8,
        "name": "Resourcess",
        "image": "/assets/images/categories/d826d52f-198f-49cc-8e65-18c1daef6bd3.png",
        "logo": "/assets/images/categories/41f64df9-83ff-40d8-a9ab-1260f7fa34cb.png",
        "description": "<p>Resourcess</p>",
        "status": 1,
        "createdAt": "2021-08-12T13:43:35.000Z",
        "updatedAt": "2021-10-06T12:20:47.000Z"
      },
      {
        "id": 6,
        "name": "House Essentials",
        "image": "/assets/images/categories/4ff54fb5-4b7e-4f11-b1f2-ae6369721443.png",
        "logo": "/assets/images/logo/991f8d5c-fbe4-4639-8e5c-9b22a5a9b6ba.png",
        "description": "<p>House Essentials</p>",
        "status": 1,
        "createdAt": "2021-08-12T13:29:23.000Z",
        "updatedAt": "2021-09-20T07:06:01.000Z"
      },
      {
        "id": 5,
        "name": "Feeding",
        "image": "/assets/images/categories/86669ac5-9018-4661-971d-35b9dbc5babb.png",
        "logo": "/assets/images/logo/ffcf5cc7-d923-4d12-82ec-69a3b2ee718a.png",
        "description": "<p>Feeding</p>",
        "status": 1,
        "createdAt": "2021-08-12T11:51:40.000Z",
        "updatedAt": "2021-09-20T07:06:15.000Z"
      },
      {
        "id": 4,
        "name": "Medical/Health",
        "image": "/assets/images/categories/41f64df9-83ff-40d8-a9ab-1260f7fa34cb.png",
        "logo": "/assets/images/logo/b215aacd-a6b4-4859-98c1-b26ae82403e5.png",
        "description": "<h2><span style=\"color: #e03e2d;\"><strong>Your Complete Guide to First-Year Puppy Vaccinations</strong></span></h2>\r\n<p>When you bring that soft, sweet-smelling little ball of puppy fuzz into your home, you know right away that she depends on you for, well, everything. It&rsquo;s up to you to give her all the care she needs every day. It can be a little intimidating &mdash; she needs the&nbsp;<a href=\"https://prf.hn/click/camref:1100l9KzB/ar:ea-puppy-shots-schedule/destination:https%3A%2F%2Fwww.chewy.com%2Fb%2Fpuppy-11097\" target=\"_blank\" rel=\"noopener\">best puppy food</a>, plenty of attention.</p>\r\n<h2><span style=\"color: #e03e2d;\"><strong>Your Complete Guide to First-Year Puppy Vaccinations</strong></span></h2>\r\n<p>When you bring that soft, sweet-smelling little ball of puppy fuzz into your home, you know right away that she depends on you for, well, everything. It&rsquo;s up to you to give her all the care she needs every day. It can be a little intimidating &mdash; she needs the&amp;am</p>\r\n<p>&nbsp;</p>\r\n<h2><span style=\"color: #e03e2d;\"><strong>Your Complete Guide to First-Year Puppy Vaccinations</strong></span></h2>\r\n<p>When you bring that soft, sweet-smelling little ball of puppy fuzz into your home, you know right away that she depends on you for, well, everything. It&rsquo;s up to you to give her all the care she needs every day. It can be a little intimidating &mdash; she needs the&nbsp;<a href=\"https://prf.hn/click/camref:1100l9KzB/ar:ea-puppy-shots-schedule/destination:https%3A%2F%2Fwww.chewy.com%2Fb%2Fpuppy-11097\" target=\"_blank\" rel=\"noopener\">best puppy food</a>, plenty of attention.</p>\r\n<h2><span style=\"color: #e03e2d;\"><strong>Your Complete Guide to First-Year Puppy Vaccinations</strong></span></h2>\r\n<p>When you bring that soft, sweet-smelling little ball of puppy fuzz into your home, you know right away that she depends on you for, well, everything. It&rsquo;s up to you to give her all the care she needs every day. It can be a little intimidating &mdash; she needs the&amp;am</p>",
        "status": 1,
        "createdAt": "2021-08-12T10:09:51.000Z",
        "updatedAt": "2022-02-10T12:34:12.000Z"
      },
      {
        "id": 3,
        "name": "Training",
        "image": "/assets/images/categories/fadad4e8-a3cc-4944-8d00-7adb50678668.png",
        "logo": "/assets/images/logo/7065fbcf-6a2e-4ede-9c44-34edc48c1868.png",
        "description": "<p>Training</p>",
        "status": 1,
        "createdAt": "2021-08-12T10:06:24.000Z",
        "updatedAt": "2021-10-06T12:34:10.000Z"
      },
      {
        "id": 2,
        "name": "Toys and Chews",
        "image": "/assets/images/categories/d0f4af69-903f-4a52-896e-3065617997e4.png",
        "logo": "",
        "description": "<p>Toys and Chews</p>",
        "status": 1,
        "createdAt": "2021-08-12T10:05:38.000Z",
        "updatedAt": "2021-09-16T09:10:35.000Z"
      }
    ],
    "Pets": [
      {
        "id": 168,
        "name": "fluffy",
        "image": "/assets/images/pets/1644404520697-file.jpg",
        "type": 0
      },
      {
        "id": 171,
        "name": "Pet owner",
        "image": "/assets/images/pets/1644490417315-file.jpg",
        "type": 0
      },
      {
        "id": 177,
        "name": "dtt",
        "image": "/assets/images/pets/1644495492393-file.jpg",
        "type": 0
      }
    ],
    "notificationsCount": 1
  }
}
 */