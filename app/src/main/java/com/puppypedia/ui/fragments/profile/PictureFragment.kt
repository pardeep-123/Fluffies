package com.puppypedia.ui.fragments.profile

import AddImageModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.last.manager.restApi.Status
import com.puppypedia.R
import com.puppypedia.common_adapters.PictureAdapter
import com.puppypedia.model.GetImageModel
import com.puppypedia.restApi.RestObservable
import com.puppypedia.ui.commomModel.ImageUploadResponse
import com.puppypedia.ui.main.ui.AllViewModel
import com.puppypedia.ui.main.ui.category_detail.DeleteResponse
import com.puppypedia.ui.main.ui.mypetprofile.MyPetProfileActivity
import com.puppypedia.utils.helper.others.Helper
import com.puppypedia.utils.helper.others.ValidationsClass
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.api.widget.Widget
import com.zxy.tiny.Tiny
import kotlinx.android.synthetic.main.fragment_picture.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class PictureFragment : Fragment(), PictureAdapter.OnDeletePic, Observer<RestObservable> {
   var adapter : PictureAdapter?=null
    private lateinit var mValidationClass: ValidationsClass
    private var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
    private var image = ""
    private var delPosition = -1
   // arraylist for images
    var images = ArrayList<MultipartBody.Part?>()
    var myList  = ArrayList<GetImageModel.Body>()
    private val viewModel: AllViewModel
            by lazy { ViewModelProviders.of(this).get(AllViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mValidationClass = ValidationsClass.getInstance()
        setAdapter(myList)
        hitGetPictureApi()
        add_fab.setOnClickListener {
            selectImage()
        }
    }
    private fun setAdapter(list: ArrayList<GetImageModel.Body>) {
        adapter = PictureAdapter(this,list)
        rvPicture.adapter = adapter
    }


    // function for images upload
    private fun callUploadPetApi() {
            val map = HashMap<String, RequestBody>()
            map["folder"] = mValidationClass.createPartFromString("pets")
            viewModel.imageUploadmultile(requireActivity(), true, map, images)
            viewModel.mResponse.observe(viewLifecycleOwner, this)

    }

    private fun selectImage() {
        Album.image(this).singleChoice().camera(true).columnCount(4).widget(
            Widget.newDarkBuilder(requireContext()).title(getString(R.string.app_name)).build()
        )
            .onResult { result ->
                mAlbumFiles.addAll(result)
             //   Glide.with(this).load(result[0].path).into(imageView)

                    image = result[0].path
                    multipartImageGet()

            }.onCancel {
            }.start()

    }

    private fun multipartImageGet(): MultipartBody.Part {

        val imageFile: MultipartBody.Part
        val options = Tiny.FileCompressOptions()
        val result =
            Tiny.getInstance().source(image).asFile().withOptions(options)
                .compressSync()
        val fileReqBody = File(result.outfile).asRequestBody("image/*".toMediaTypeOrNull())
        imageFile =
            MultipartBody.Part.createFormData(
                "image",
                System.currentTimeMillis().toString() + ".jpg",
                fileReqBody
            )

        images.add(imageFile)
        // hit upload api for image
        callUploadPetApi()
        return imageFile

    }
    private fun hitAddPictureApi(){
        val map = HashMap<String, RequestBody>()
        map["pet_id"] = mValidationClass.createPartFromString((activity as MyPetProfileActivity).petId)
        map["pet_image"] = mValidationClass.createPartFromString(image)
        viewModel.addPicture(requireActivity(), true, map)
        viewModel.mResponse.observe(this, this)
    }

    // get picture list api
    private fun hitGetPictureApi(){
        val map = HashMap<String, RequestBody>()
        map["pet_id"] = mValidationClass.createPartFromString((activity as MyPetProfileActivity).petId)
        viewModel.getPicture(requireActivity(), true, map)
        viewModel.mResponse.observe(viewLifecycleOwner, this)
    }

    override fun onDeletePic(id:String,position:Int) {
        delPosition = position
        val map = HashMap<String, RequestBody>()
        map["image_id"] = mValidationClass.createPartFromString(id)
        viewModel.delPicture(requireActivity(), true, map)
        viewModel.mResponse.observe(viewLifecycleOwner, this)
    }

    override fun onChanged(liveData: RestObservable?) {
        when{
            liveData!!.status == Status.SUCCESS->{
                if (liveData.data is ImageUploadResponse) {
                    image = liveData.data.body[0].image
                    hitAddPictureApi()
                } else if(liveData.data is AddImageModel){
                   hitGetPictureApi()
//                    myList.add(liveData.data.body)
//                    adapter?.notifyDataSetChanged()
                } else if (liveData.data is GetImageModel){
                    if (liveData.data.body.size>0)
                        myList.clear()
                    myList.addAll(liveData.data.body)
                    adapter?.notifyDataSetChanged()
                }  else if (liveData.data is DeleteResponse){
                    myList.removeAt(delPosition)
                    adapter?.notifyDataSetChanged()
                }
            }
            liveData.status == Status.ERROR -> {
                if (liveData.data != null) {
                    Helper.showErrorAlert(requireActivity(), liveData.data as String)
                } else {
                    Helper.showErrorAlert(requireActivity(), liveData.error.toString())
                }
            }
        }
    }
}