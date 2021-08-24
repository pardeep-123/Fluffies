package com.puppypedia.ui.main.ui.mypetprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.common_adapters.StatusAdapter
import com.puppypedia.databinding.ActivityMyPetProfileBinding
import com.puppypedia.ui.main.ui.editpetprofile.EditPetProfileActivity

class MyPetProfileActivity : AppCompatActivity() {

    lateinit var adapter:StatusAdapter
    lateinit var binding:ActivityMyPetProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPetProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
                clickHandle()
        val arrayList = ArrayList<Int>()
        arrayList.add(R.drawable.petprofile)
        arrayList.add(R.drawable.petprofile_one)
        arrayList.add(R.drawable.plusbg)

        binding.tb.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.tb.tvTitle.text = getString(R.string.my_pet_profile)

        adapter = StatusAdapter(this,arrayList)
        binding.rvStatus.adapter = adapter

    }

    fun clickHandle(){

        binding.BtnEdit.setOnClickListener {
            startActivity(Intent(this,EditPetProfileActivity::class.java))
        }

    }
}